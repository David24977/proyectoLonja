import { useState } from "react";
import axios from "axios";
import AutocompleteCliente from "./AutoCompleteCliente";
import { API_BASE_URL } from "./config";

export default function HieloClientes() {
  const [cliente, setCliente] = useState(null);
  const [cantidad, setCantidad] = useState("");
  const [fecha, setFecha] = useState("");
  const [mensaje, setMensaje] = useState("");

  const handleSubmit = async (e) => {
    e.preventDefault();
    if (!cliente || !cliente.id || !cantidad || !fecha) {
      setMensaje("Rellena todos los campos.");
      return;
    }
    try {
      await axios.post(`${API_BASE_URL}/api/hielo-cliente`, {
        clienteId: cliente.id,
        cantidad: Number(cantidad),
        fecha,
      });
      setMensaje("Producción registrada correctamente ✅");
      setCliente(null);
      setCantidad("");
      setFecha("");
    } catch (error) {
      setMensaje("Error al registrar producción ❌");
      console.error("Error:", error);
    }
  };

  return (
    <div className="form-box">
      <h2>Insertar producción de hielo (Clientes)</h2>
      {mensaje && <div>{mensaje}</div>}
      <form onSubmit={handleSubmit}>
        <div>
          <label>Cliente:</label>
          <AutocompleteCliente onSelect={setCliente} />
        </div>
        <div>
          <label>Cantidad de hielo:</label>
          <input
            type="number"
            min={0}
            step={0.5}
            value={cantidad}
            onChange={e => setCantidad(e.target.value)}
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
        <button type="submit">Registrar producción</button>
      </form>
    </div>
  );
}
