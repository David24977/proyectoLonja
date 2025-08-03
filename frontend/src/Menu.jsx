export default function Menu({ setPantalla, tipoUsuario, onLogout }) {
  return (
    <nav style={{ marginBottom: '1rem' }}>
      {tipoUsuario && (
        <button onClick={onLogout}>Cerrar sesión</button>
      )}
      {/* Operario */}
      {tipoUsuario === 'operario' && (
        <>
          <button onClick={() => setPantalla('cajas')}>Cajas devueltas</button>
          <button onClick={() => setPantalla('hielo')}>Hielo barcas</button>
          <button onClick={() => setPantalla('hieloClientes')}>Hielo clientes</button>
          <button onClick={() => setPantalla('resumenHieloBarcasDia')}>Resumen diario hielo barcas</button>
          <button onClick={() => setPantalla('resumenHieloClientesDia')}>Resumen diario hielo clientes</button>
          <button onClick={() => setPantalla('resumenCajasDia')}>Resumen diario cajas clientes</button>
        </>
      )}
      {/* Oficina/Admin */}
      {tipoUsuario === 'oficina' && (
        <>
          <button onClick={() => setPantalla('resumenCajas')}>Resumen devoluciones cajas por fechas</button>
          <button onClick={() => setPantalla('resumenHieloClientes')}>Resumen hielo clientes por fechas</button>
          <button onClick={() => setPantalla('resumenHieloBarca')}>Resumen hielo barcas por fechas</button>
          <button onClick={() => setPantalla('adminClientes')}>Crear cliente</button>
          <button onClick={() => setPantalla('adminBarcas')}>Crear barca</button>
          <button onClick={() => setPantalla('eliminarClientes')}>Eliminar cliente</button>
          <button onClick={() => setPantalla('eliminarBarcas')}>Eliminar barca</button>
          <button onClick={() => setPantalla('listarClientes')}>Lista Clientes</button>
           <button onClick={() => setPantalla('listarBarcas')}>Lista Barcas</button>


        </>
      )}
    </nav>
  );
}
