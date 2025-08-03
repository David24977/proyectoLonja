import { useState } from "react";
import axios from "axios";
import { API_BASE_URL } from "./config";

export default function InsertarCliente() {
  const [id, setId] = useState("");          // <-- Nuevo campo
  const [nombre, setNombre] = useState("");
  const [email, setEmail] = useState("");
  const [mensaje, setMensaje] = useState("");

  const handleSubmit = async (e) => {
    e.preventDefault();

    // Validaciones frontend
    if (!id.trim() || !nombre.trim() || !email.trim()) {
      setMensaje("Debes poner id, nombre y email.");
      return;
    }
    if (!/^\d+$/.test(id)) {
      setMensaje("El id debe ser un número.");
      return;
    }
    if (!/\S+@\S+\.\S+/.test(email)) {
      setMensaje("Introduce un email válido.");
      return;
    }
    if (nombre.length > 40) {
      setMensaje("El nombre es demasiado largo.");
      return;
    }

    try {
      await axios.post(`${API_BASE_URL}/clientes/registrar`, {
        id: Number(id),           // <-- Enviamos id también
        nombre: nombre.trim(),
        email: email.trim()
      });
      setMensaje("✅ Cliente insertado correctamente");
      setId("");
      setNombre("");
      setEmail("");
    } catch (error) {
  if (error.response) {
    if (error.response.status === 409) {
      // Busca el mensaje en error o en message
      const msg = (error.response.data.error || error.response.data.message || "❌ Ya existe un cliente con ese id.");
      setMensaje(msg);
    } else if (error.response.status === 400) {
      setMensaje("❌ Error en los datos enviados. Revisa id, nombre y email.");
    } else {
      setMensaje("❌ Error al insertar cliente");
    }
  } else {
    setMensaje("❌ Error de conexión");
  }
}
  };


  return (
    <div>
      <h2>Crear cliente</h2>
      <form onSubmit={handleSubmit}>
        <div>
          <label>ID:</label>
          <input
            value={id}
            onChange={e => setId(e.target.value)}
            autoComplete="off"
            placeholder="Introduce id (número)"
            type="number"
            
          />
        </div>
        <div>
          <label>Nombre:</label>
          <input
            value={nombre}
            onChange={e => setNombre(e.target.value)}
            autoComplete="off"
            placeholder="Introduce nombre"
          />
        </div>
        <div>
          <label>Email:</label>
          <input
            value={email}
            onChange={e => setEmail(e.target.value)}
            type="email"
            autoComplete="off"
            placeholder="Introduce email"
          />
        </div>
        <button type="submit">Insertar</button>
      </form>
      {mensaje && <p>{mensaje}</p>}
    </div>
  );
}
