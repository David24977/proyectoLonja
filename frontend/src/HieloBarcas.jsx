import { useState } from "react";
import axios from "axios";
import AutocompleteBarca from "./AutocompleteBarca"; // Asegúrate que está en el mismo directorio
import { API_BASE_URL } from "./config";

export default function HieloBarcas() {
  const [barca, setBarca] = useState(null); // Objeto barca seleccionado
  const [cantidad, setCantidad] = useState("");
  const [fecha, setFecha] = useState("");
  const [mensaje, setMensaje] = useState("");

  const handleSubmit = async (e) => {
    e.preventDefault();
    // Validación básica
    if (!barca || !barca.id || !cantidad || !fecha) {
      setMensaje("Rellena todos los campos.");
      return;
    }

    try {
      await axios.post(`${API_BASE_URL}/api/hielo`, {
        barca: { id: barca.id },
        cantidad: Number(cantidad),
        fecha
      });
      setMensaje("Producción de hielo registrada correctamente ✅");
      setCantidad("");
      setFecha("");
      // Puedes limpiar la barca si quieres:
      // setBarca(null);
    } catch (error) {
      setMensaje("Error al registrar la producción de hielo ❌");
      console.error("Error al registrar hielo:", error);
    }
  };

  return (
    <div>
      <h2>Insertar producción de hielo por barca</h2>
      <form onSubmit={handleSubmit}>
        <div>
          <label>Barca:</label>
          <AutocompleteBarca onSelect={setBarca} />
        </div>
        <div>
          <label>Cantidad de hielo:</label>
          <input
            type="number"
            value={cantidad}
            onChange={e => setCantidad(e.target.value)}
            min={0.1}
            step={0.1}
            required
          />
        </div>
        <div>
          <label>Fecha:</label>
          <input
            type="date"
            value={fecha}
            onChange={e => setFecha(e.target.value)}
            required
          />
        </div>
        <button type="submit">Registrar hielo</button>
      </form>
      {mensaje && <p>{mensaje}</p>}
      {barca && (
        <p>
          <strong>Barca seleccionada:</strong> {barca.nombre} (ID: {barca.id})
        </p>
      )}
    </div>
  );
}
