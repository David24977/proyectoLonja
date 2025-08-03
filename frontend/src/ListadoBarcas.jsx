import { useState, useEffect } from "react";
import { API_BASE_URL } from "./config";
import axios from "axios";

export default function ListadoBarcas() {
  const [barcas, setBarcas] = useState([]);
  const [mensaje, setMensaje] = useState("");

  useEffect(() => {
    axios.get(`${API_BASE_URL}/api/barcas`)
      .then(resp => setBarcas(resp.data))
      .catch(error => {
        setMensaje("Error al cargar barcas");
        console.error(error);
      });
  }, []);

  return (
    <div>
      <h2>Listado de barcas</h2>
      {mensaje && <p>{mensaje}</p>}
      <table style={{
        width: "100%",
        background: "#fff",
        borderRadius: "12px",
        boxShadow: "0 2px 8px #b9cfff1f",
        marginTop: "2em"
      }}>
        <thead>
          <tr>
            <th style={{padding:"0.6em", background:"#e3eafe", color:"#25408f"}}>ID</th>
            <th style={{padding:"0.6em", background:"#e3eafe", color:"#25408f"}}>Nombre</th>
          </tr>
        </thead>
        <tbody>
          {barcas.length === 0
            ? <tr><td colSpan={2} style={{textAlign:"center", padding:"1em"}}>No hay barcas registradas</td></tr>
            : barcas.map(barca => (
                <tr key={barca.id}>
                  <td style={{padding:"0.5em"}}>{barca.id}</td>
                  <td style={{padding:"0.5em"}}>{barca.nombre}</td>
                </tr>
            ))
          }
        </tbody>
      </table>
    </div>
  );
}
