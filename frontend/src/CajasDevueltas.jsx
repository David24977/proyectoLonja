import { useState } from "react";
import axios from "axios";
import AutocompleteCliente from "./AutoCompleteCliente";
import { API_BASE_URL } from "./config";


export default function CajasDevueltas() {
  const [cliente, setCliente] = useState(null); // Guarda {id, nombre}
  const [numeroCajas, setNumeroCajas] = useState("");
  const [fecha, setFecha] = useState("");
  const [mensaje, setMensaje] = useState("");

  const handleSubmit = async (e) => {
    e.preventDefault();
    console.log("cliente:", cliente);
    console.log("numeroCajas:", numeroCajas);
    console.log("fecha:", fecha);
    alert("cliente: " + JSON.stringify(cliente));


    // Validación básica
    if (!cliente || !cliente.id || !numeroCajas || !fecha) {
      setMensaje("Rellena todos los campos.");
      return;
    }

    try {
      await axios.post(`${API_BASE_URL}/cajas/insertar`, {
        clienteId: cliente.id,
        numeroCajas: Number(numeroCajas),
        fecha,
      });
      setMensaje("Devolución registrada correctamente ✅");
      setCliente(null); // Limpia el autocomplete
      setNumeroCajas("");
      setFecha("");
    } catch (error) {
      setMensaje("Error al registrar devolución ❌");
      console.error("Error al registrar devolución:", error);
    }
  };

  return (
    <div>
      <h2>Insertar devolución de cajas</h2>
      <form onSubmit={handleSubmit}>
        <div>
          <label>Cliente:</label>
          <AutocompleteCliente onSelect={setCliente} />
        </div>
        <div>
          <label>Nº Cajas:</label>
          <input
            type="number"
            value={numeroCajas}
            onChange={e => setNumeroCajas(e.target.value)}
            min={1}
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
        <button type="submit">Registrar devolución</button>
      </form>
      {mensaje && <p>{mensaje}</p>}
    </div>
  );
}