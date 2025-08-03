import { useState } from "react";
import axios from "axios";
import { API_BASE_URL } from "./config";

export default function EliminarCliente() {
  const [busqueda, setBusqueda] = useState("");
  const [resultados, setResultados] = useState([]);
  const [mensaje, setMensaje] = useState("");

  const buscarClientes = async () => {
    if (!busqueda.trim()) {
      setResultados([]);
      return;
    }
    try {
      const resp = await axios.get(`${API_BASE_URL}/clientes/buscar/parcial`, {
        params: { nombre: busqueda }
      });
      setResultados(resp.data);
    } catch (error) {
      setResultados([]);
      setMensaje("Error al buscar clientes.");
      console.error("Error al buscar clientes", error)
    }
  };

  const eliminarCliente = async (id) => {
    if (!window.confirm("¿Seguro que quieres eliminar este cliente?")) return;
    try {
      await axios.delete(`http://192.168.0.123:8080/clientes/eliminar/${id}`);
      setMensaje("✅ Cliente eliminado.");
      setResultados(resultados.filter(c => c.id !== id));
    } catch (error) {
      if (
        error.response &&
        error.response.data &&
        typeof error.response.data === "string" &&
        error.response.data.toLowerCase().includes("integridad")
      ) {
        setMensaje("❌ No se puede eliminar el cliente porque tiene registros asociados. Borra primero sus cajas y hielo.");
      } else {
        setMensaje("❌ Error al eliminar cliente.");
      }
      console.error(error);
    }
  };

  return (
    <div>
      <h2>Eliminar cliente</h2>
      <input
        placeholder="Buscar cliente por nombre"
        value={busqueda}
        onChange={e => setBusqueda(e.target.value)}
        onKeyDown={e => e.key === "Enter" && buscarClientes()}
        style={{ marginRight: 8 }}
      />
      <button onClick={buscarClientes}>Buscar</button>
      {mensaje && <p>{mensaje}</p>}
      <ul>
        {resultados.map(cliente => (
          <li key={cliente.id}>
            {cliente.nombre} ({cliente.email}){" "}
            <button
              onClick={() => eliminarCliente(cliente.id)}
              style={{
                background: "none",
                color: "#d7263d",
                border: "none",
                fontWeight: "bold",
                cursor: "pointer"
              }}
              title="Eliminar cliente"
            >
              🗑️
            </button>
          </li>
        ))}
      </ul>
    </div>
  );
}
