import { useState } from "react";
import axios from "axios";
import { API_BASE_URL } from "./config";

export default function InsertarBarca() {
  const [id, setId] = useState("");  // Nuevo campo
  const [nombre, setNombre] = useState("");
  const [mensaje, setMensaje] = useState("");

  const handleSubmit = async (e) => {
    e.preventDefault();

    // Validaciones frontend
    if (!id || isNaN(Number(id))) {
      setMensaje("Debes indicar un ID numérico para la barca.");
      return;
    }
    if (!nombre.trim()) {
      setMensaje("Debes poner el nombre de la barca.");
      return;
    }
    if (nombre.length > 40) {
      setMensaje("El nombre es demasiado largo.");
      return;
    }

    try {
      await axios.post(`${API_BASE_URL}/api/barcas/insertar`, {
        id: Number(id),      // Envía también el id
        nombre: nombre.trim()
      });
      setMensaje("✅ Barca insertada correctamente");
      setId("");
      setNombre("");
    } catch (error) {
      if (
        error.response &&
        error.response.data &&
        typeof error.response.data === "string" &&
        error.response.data.toLowerCase().includes("id")
      ) {
        setMensaje("❌ Ya existe una barca con ese id.");
      } else if (
        error.response &&
        error.response.data &&
        typeof error.response.data === "string" &&
        error.response.data.toLowerCase().includes("nombre")
      ) {
        setMensaje("❌ Ya existe una barca con ese nombre.");
      } else {
        setMensaje("❌ Error al insertar barca");
      }
      console.error("Error al insertar barca:", error);
    }
  };

  return (
    <div>
      <h2>Crear barca</h2>
      <form onSubmit={handleSubmit}>
        <div>
          <label>ID:</label>
          <input
            value={id}
            onChange={e => setId(e.target.value.replace(/\D/, ""))} // Solo números
            autoComplete="off"
            placeholder="Introduce ID"
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
        <button type="submit">Insertar</button>
      </form>
      {mensaje && <p>{mensaje}</p>}
    </div>
  );
}
