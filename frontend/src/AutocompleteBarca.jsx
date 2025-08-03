import { useState } from "react";
import axios from "axios";
import { API_BASE_URL } from "./config";

export default function AutocompleteBarca({ onSelect }) {
  const [input, setInput] = useState("");
  const [sugerencias, setSugerencias] = useState([]);
  const [highlightedIndex, setHighlightedIndex] = useState(-1);
  const [seleccionado, setSeleccionado] = useState(false);

  const handleClear = () => {
    setInput("");
    setSugerencias([]);
    setSeleccionado(false);
    setHighlightedIndex(-1);
    onSelect(null);
  };

  const handleChange = async (e) => {
    const valor = e.target.value;
    setInput(valor);
    setHighlightedIndex(-1);

    if (valor === "") {
      setSugerencias([]);
      setSeleccionado(false);
      onSelect(null);
      return;
    }

    if (seleccionado) {
      setSeleccionado(false);
      return;
    }

    if (valor.length >= 2) {
      try {
        const resp = await axios.get(`${API_BASE_URL}/api/barcas/buscar/parcial`, {
          params: { nombre: valor }
        });
        setSugerencias(resp.data);
        if (resp.data.length === 1) {
          setInput(resp.data[0].nombre);
          setSugerencias([]);
          setHighlightedIndex(-1);
          setSeleccionado(true);
          onSelect(resp.data[0]);
        }
      } catch {
        setSugerencias([]);
      }
    } else {
      setSugerencias([]);
    }
  };

  const handleSelect = (barca) => {
    setInput(barca.nombre);
    setSugerencias([]);
    setHighlightedIndex(-1);
    setSeleccionado(true);
    onSelect(barca);
  };

  const handleKeyDown = (e) => {
    if (!sugerencias.length) return;
    if (e.key === "ArrowDown") {
      setHighlightedIndex((prev) =>
        prev < sugerencias.length - 1 ? prev + 1 : 0
      );
    } else if (e.key === "ArrowUp") {
      setHighlightedIndex((prev) =>
        prev > 0 ? prev - 1 : sugerencias.length - 1
      );
    } else if (e.key === "Enter") {
      if (highlightedIndex >= 0 && highlightedIndex < sugerencias.length) {
        handleSelect(sugerencias[highlightedIndex]);
        e.preventDefault();
      }
    }
  };

  return (
    <div style={{ position: "relative" }}>
      <input
        type="text"
        value={input}
        onChange={handleChange}
        onKeyDown={handleKeyDown}
        placeholder="Busca barca..."
        autoComplete="off"
      />
      {/* Botón para limpiar el campo */}
      {input && (
        <button
          type="button"
          onClick={handleClear}
          style={{
            position: "absolute",
            right: 6,
            top: "50%",
            transform: "translateY(-50%)",
            background: "transparent",
            border: "none",
            fontSize: "0.7em",
            color: "#888",
            cursor: "pointer",
            zIndex: 1100
          }}
          title="Limpiar"
          tabIndex={-1}
        >❌</button>
      )}
      {sugerencias.length > 0 && (
        <ul style={{
          position: "absolute",
          zIndex: 1000,
          background: "#e7e7dcff",
          border: "2px solid blue",
          color: "#0d0d0d",
          margin: 0,
          padding: "0.5em",
          listStyle: "none",
          width: "300px",
          fontWeight: "bold",
          fontSize: "1.1em"
        }}>
          {sugerencias.map((barca, idx) => (
            <li
              key={barca.id}
              style={{
                cursor: "pointer",
                background: idx === highlightedIndex ? "#bde4ff" : "transparent",
                fontWeight: idx === highlightedIndex ? "bold" : "normal"
              }}
              onClick={() => handleSelect(barca)}
              onMouseEnter={() => setHighlightedIndex(idx)}
            >
              {barca.nombre}
            </li>
          ))}
        </ul>
      )}
    </div>
  );
}
