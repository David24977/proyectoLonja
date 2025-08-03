import { useState } from "react";
import axios from "axios";
import { API_BASE_URL } from "./config";

export default function EliminarBarca() {
  const [busqueda, setBusqueda] = useState("");
  const [resultados, setResultados] = useState([]);
  const [mensaje, setMensaje] = useState("");

  const buscarBarcas = async () => {
    if (!busqueda.trim()) {
      setResultados([]);
      return;
    }
    try {
      const resp = await axios.get(`${API_BASE_URL}/api/barcas/buscar/parcial`, {
        params: { nombre: busqueda }
      });
      setResultados(resp.data);
    } catch (error) {
      setResultados([]);
      setMensaje("Error al buscar barcas.");
      console.error("Error al insertar barca:", error);

    }
  };

  const eliminarBarca = async (id) => {
    if (!window.confirm("¿Seguro que quieres eliminar esta barca?")) return;
    try {
      await axios.delete(`http://192.168.0.123:8080/api/barcas/${id}`);
      setMensaje("✅ Barca eliminada.");
      setResultados(resultados.filter(b => b.id !== id));
    } catch (error) {
      setMensaje("❌ Error al eliminar barca.");
      console.error(error);
    }
  };

  return (
    <div>
      <h2>Eliminar barca</h2>
      <input
        placeholder="Buscar barca por nombre"
        value={busqueda}
        onChange={e => setBusqueda(e.target.value)}
        onKeyDown={e => e.key === "Enter" && buscarBarcas()}
        style={{ marginRight: 8 }}
      />
      <button onClick={buscarBarcas}>Buscar</button>
      {mensaje && <p>{mensaje}</p>}
      <ul>
        {resultados.map(barca => (
          <li key={barca.id}>
            {barca.nombre}
            <button onClick={() => eliminarBarca(barca.id)}>
              🗑️
            </button>
          </li>
        ))}
      </ul>
    </div>
  );
}
