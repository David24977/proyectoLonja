import { useEffect, useState } from "react";
import axios from "axios";
import { API_BASE_URL } from "./config";

function ListaCajas() {
  const [cajas, setCajas] = useState([]);

  useEffect(() => {
    axios.get(`${API_BASE_URL}/cajas/cliente/1`)
      .then(response => setCajas(response.data))
      .catch(error => console.error("Error al cargar cajas:", error));
  }, []);

  return (
    <div>
      <h2>Devoluciones de cajas (Cliente 1)</h2>
      <ul>
        {cajas.length === 0
          ? <li>No hay devoluciones para este cliente.</li>
          : cajas.map((caja) => (
            <li key={caja.id}>
              Cliente: {caja.nombreCliente} | Cajas: {caja.numeroCajas} | Fecha: {caja.fecha}
            </li>
          ))
        }
      </ul>
    </div>
  );
}

export default ListaCajas;
