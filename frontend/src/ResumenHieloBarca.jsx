import { useState } from "react";
import axios from "axios";
import AutocompleteBarca from "./AutocompleteBarca";
import { API_BASE_URL } from "./config";

export default function ResumenHieloBarca() {
  const [barca, setBarca] = useState(null);
  const [desde, setDesde] = useState("");
  const [hasta, setHasta] = useState("");
  const [listado, setListado] = useState([]);
  const [mensaje, setMensaje] = useState("");

  const consultarResumen = async (e) => {
    e.preventDefault();
    if (!barca || !barca.id || !desde || !hasta) {
      setMensaje("Selecciona barca y fechas.");
      setListado([]);
      return;
    }
    try {
      const resp = await axios.get(`${API_BASE_URL}/api/hielo/barca/${barca.id}/rango`, {
        params: { desde, hasta }
      });
      setListado(resp.data);
      setMensaje("");
    } catch (error) {
      setMensaje("Error al consultar resumen.");
      setListado([]);
      console.error("Error al consultar resumen:", error);
    }
  };

  // Total de hielo producido
  const total = listado.reduce((acc, hielo) => acc + hielo.cantidad, 0);

  return (
    <div>
      <h2>Resumen de hielo por barca</h2>
      <form onSubmit={consultarResumen}>
        <div>
          <label>Barca:</label>
          <AutocompleteBarca onSelect={setBarca} />
        </div>
        <div>
          <label>Desde:</label>
          <input
            type="date"
            value={desde}
            onChange={e => setDesde(e.target.value)}
            required
          />
        </div>
        <div>
          <label>Hasta:</label>
          <input
            type="date"
            value={hasta}
            onChange={e => setHasta(e.target.value)}
            required
          />
        </div>
        <button type="submit">Consultar resumen</button>
      </form>
      {mensaje && <p>{mensaje}</p>}
      {barca && (
        <p>
          <strong>Barca seleccionada:</strong> {barca.nombre} (ID: {barca.id})
        </p>
      )}
      <p><strong>Total de hielo producido:</strong> {total}</p>
      <h3>Listado de producciones filtradas</h3>
      <table style={{
        width: "100%",
        background: "#fff",
        borderRadius: "12px",
        boxShadow: "0 2px 8px #b9cfff1f",
        marginTop: "2em"
      }}>
        <thead>
          <tr>
            <th style={{ padding: "0.6em", background: "#e3eafe", color: "#25408f" }}>Barca</th>
            <th style={{ padding: "0.6em", background: "#e3eafe", color: "#25408f" }}>Hielo (sacos)</th>
            <th style={{ padding: "0.6em", background: "#e3eafe", color: "#25408f" }}>Fecha</th>
            <th style={{ padding: "0.6em", background: "#e3eafe", color: "#25408f" }}>Acción</th>
          </tr>
        </thead>
        <tbody>
          {listado.length === 0
            ? (
              <tr>
                <td colSpan={4} style={{ padding: "1.5em", textAlign: "center", color: "#888" }}>
                  No hay registros en este rango.
                </td>
              </tr>
            )
            : listado.map(hielo => (
              <tr key={hielo.id}>
                <td style={{ padding: "0.5em" }}>{hielo.barca?.nombre ?? "—"}</td>
                <td style={{ padding: "0.5em" }}>{hielo.cantidad}</td>
                <td style={{ padding: "0.5em" }}>{hielo.fecha}</td>
                <td style={{ padding: "0.5em" }}>
                  <button
                    onClick={async () => {
                      if (window.confirm("¿Seguro que quieres borrar este registro?")) {
                        try {
                          await axios.delete(`${API_BASE_URL}/api/hielo/${hielo.id}`);
                          setListado(listado.filter(h => h.id !== hielo.id));
                        } catch (error) {
                          alert("Error al borrar el registro");
                          console.error(error);
                        }
                      }
                    }}
                    style={{
                      background: "none",
                      border: "none",
                      color: "#d7263d",
                      fontSize: "1.4em",
                      cursor: "pointer"
                    }}
                    title="Eliminar registro"
                  >
                    🗑️
                  </button>
                </td>
              </tr>
            ))
          }
        </tbody>
      </table>
    </div>
  );
}
