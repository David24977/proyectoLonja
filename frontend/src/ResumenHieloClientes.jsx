import { useState } from "react";
import axios from "axios";
import AutocompleteCliente from "./AutoCompleteCliente";
import { API_BASE_URL } from "./config";

export default function ResumenHieloClientes() {
  const [cliente, setCliente] = useState(null);
  const [desde, setDesde] = useState("");
  const [hasta, setHasta] = useState("");
  const [listado, setListado] = useState([]);
  const [mensaje, setMensaje] = useState("");

  const consultarResumen = async (e) => {
    e.preventDefault();
    if (!cliente || !cliente.id || !desde || !hasta) {
      setMensaje("Selecciona cliente y rellena fechas.");
      setListado([]);
      return;
    }
    try {
      const respListado = await axios.get(
        `${API_BASE_URL}/api/hielo-cliente/cliente/` + cliente.id + "/rango",
        { params: { desde, hasta } }
      );
      setListado(respListado.data);
      setMensaje("");
    } catch (error) {
      setMensaje("Error al consultar el resumen.");
      setListado([]);
      console.error("Error al consultar resumen:", error);
    }
  };

  // Total de hielo producido por el cliente
  const total = listado.reduce((acc, hielo) => acc + hielo.cantidad, 0);

  return (
    <div>
      <h2>Resumen de hielo por cliente y fechas</h2>
      <form onSubmit={consultarResumen}>
        <div>
          <label>Cliente:</label>
          <AutocompleteCliente onSelect={setCliente} />
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

      {cliente && (
        <p>
          <strong>Cliente seleccionado:</strong> {cliente.nombre} (ID: {cliente.id})
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
            <th style={{ padding: "0.6em", background: "#e3eafe", color: "#25408f" }}>Cliente</th>
            <th style={{ padding: "0.6em", background: "#e3eafe", color: "#25408f" }}>Cantidad</th>
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
                <td style={{ padding: "0.5em" }}>{hielo.nombreCliente || hielo.cliente?.nombre || "—"}</td>
                <td style={{ padding: "0.5em" }}>{hielo.cantidad}</td>
                <td style={{ padding: "0.5em" }}>{hielo.fecha}</td>
                <td style={{ padding: "0.5em" }}>
                  <button
                    onClick={async () => {
                      if (window.confirm("¿Seguro que quieres borrar este registro?")) {
                        try {
                          await axios.delete(`${API_BASE_URL}/api/hielo-cliente/${hielo.id}`);
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
