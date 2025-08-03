import { useState } from "react";

export default function Login({ onLogin }) {
  const [usuario, setUsuario] = useState("");
  const [password, setPassword] = useState("");
  const [mensaje, setMensaje] = useState("");

  const handleSubmit = (e) => {
    e.preventDefault();

    // Distinguir tipo de usuario
    let tipo = null;
    if (usuario === "admin" && password === "admin") {
      tipo = "oficina";
    } else if (usuario === "operario" && password === "operario") {
      tipo = "operario";
    } else {
      setMensaje("Usuario o contraseña incorrectos");
      return;
    }
    setMensaje("¡Login correcto!");
    onLogin(tipo); // <-- le pasamos el tipo al padre (App)
  };

  return (
    <div>
      <h2>Iniciar sesión</h2>
      <form onSubmit={handleSubmit}>
        <div>
          <label>Usuario: </label>
          <input
            type="text"
            value={usuario}
            onChange={e => setUsuario(e.target.value)}
            autoFocus
          />
        </div>
        <div>
          <label>Contraseña: </label>
          <input
            type="password"
            value={password}
            onChange={e => setPassword(e.target.value)}
          />
        </div>
        <button type="submit">Entrar</button>
      </form>
      {mensaje && <p>{mensaje}</p>}
    </div>
  );
}
