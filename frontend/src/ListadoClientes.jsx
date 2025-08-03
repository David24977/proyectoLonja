import { useState, useEffect } from "react";
import { API_BASE_URL } from "./config";
import axios from "axios";

export default function ListadoClientes() {
  const [clientes, setClientes] = useState([]);
  const [mensaje, setMensaje] = useState("");

  useEffect(() => {
    axios.get(`${API_BASE_URL}/clientes/todos`)
      .then(resp => setClientes(resp.data))
      .catch(error => {
        setMensaje("Error al cargar clientes");
        console.error(error);
      });
  }, []);

  return (
    <div>
      <h2>Listado de clientes</h2>
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
            <th style={{padding:"0.6em", background:"#e3eafe", color:"#25408f"}}>Email</th>
          </tr>
        </thead>
        <tbody>
          {clientes.length === 0
            ? <tr><td colSpan={3} style={{textAlign:"center", padding:"1em"}}>No hay clientes registrados</td></tr>
            : clientes.map(cliente => (
                <tr key={cliente.id}>
                  <td style={{padding:"0.5em"}}>{cliente.id}</td>
                  <td style={{padding:"0.5em"}}>{cliente.nombre}</td>
                  <td style={{padding:"0.5em"}}>{cliente.email}</td>
                </tr>
            ))
          }
        </tbody>
      </table>
    </div>
  );
}
