import { useState } from "react";
import axios from "axios";
import { API_BASE_URL } from "./config";

export default function ResumenHieloBarcasDia() {
  const hoy = new Date().toISOString().substring(0,10);
  const [fecha, setFecha] = useState(hoy);
  const [listado, setListado] = useState([]);
  const [mensaje, setMensaje] = useState("");

  const consultarResumen = async (e) => {
    e.preventDefault();
    if (!fecha) {
      setMensaje("Selecciona un día.");
      setListado([]);
      return;
    }
    try {
      const resp = await axios.get(`${API_BASE_URL}/api/hielo/resumen-dia`, {
        params: { fecha }
      });
      setListado(resp.data);
      setMensaje("");
    } catch (error) {
      setMensaje("Error al consultar el resumen.");
      setListado([]);
      console.error("Error al consultar resumen:", error);
    }
  };

  const total = listado.reduce((acc, item) => acc + item.cantidadTotal, 0);

  return (
    <div>
      <h2>Resumen diario de hielo por barca</h2>
      <form onSubmit={consultarResumen}>
        <div>
          <label>Día:</label>
          <input
            type="date"
            value={fecha}
            onChange={e => setFecha(e.target.value)}
            required
          />
        </div>
        <button type="submit">Consultar</button>
      </form>
      {mensaje && <p>{mensaje}</p>}

      <p><strong>Hielo total:</strong> {total}</p>
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
            <th style={{ padding: "0.6em", background: "#e3eafe", color: "#25408f" }}>Cantidad</th>
          </tr>
        </thead>
        <tbody>
          {listado.length === 0
            ? (
              <tr>
                <td colSpan={2} style={{ padding: "1.5em", textAlign: "center", color: "#888" }}>
                  No hay registros para ese día.
                </td>
              </tr>
            )
            : listado.map((item, idx) => (
              <tr key={idx}>
                <td style={{ padding: "0.5em" }}>{item.nombreBarca}</td>
                <td style={{ padding: "0.5em" }}>{item.cantidadTotal}</td>
              </tr>
            ))
          }
        </tbody>
      </table>
    </div>
  );
}
