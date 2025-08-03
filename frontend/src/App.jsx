import { useState } from 'react';
import './App.css';
import Menu from './Menu';
import Login from './Login';
import CajasDevueltas from './CajasDevueltas';
import HieloBarcas from './HieloBarcas';
import ResumenCajas from './ResumenCajas';
import ResumenHieloBarca from './ResumenHieloBarca';
import HieloClientes from './HieloClientes';
import ResumenHieloClientes from './ResumenHieloClientes';
import ResumenHieloBarcasDia from './ResumenHieloBarcasDia';
import ResumenHieloClientesDia from './ResumenHieloClientesDia';
import ResumenCajasDia from './ResumenCajasDia';
import InsertarBarca from './InsertarBarca';
import InsertarCliente from './InsertarCliente';
import EliminarCliente from './EliminarCliente';
import EliminarBarca from './EliminarBarca';
import ListadoBarcas from './ListadoBarcas';
import ListadoClientes from './ListadoClientes';


function App() {
  const [pantalla, setPantalla] = useState('login');
  const [logueado, setLogueado] = useState(false);
  const [tipoUsuario, setTipoUsuario] = useState(null); // "operario" o "oficina"

  // Recibe el tipo de usuario del Login y hace login
  const handleLogin = (tipo) => {
    setTipoUsuario(tipo);
    setLogueado(true);
    // Elige la pantalla inicial según tipo
    if (tipo === "operario") setPantalla('cajas');
    else setPantalla('resumenCajas');
  };

  // Opción para cerrar sesión
  const handleLogout = () => {
    setLogueado(false);
    setTipoUsuario(null);
    setPantalla('login');
  };

  return (
    <div>
      <h1>Llotja Cullera</h1>
      <Menu setPantalla={setPantalla} tipoUsuario={tipoUsuario} onLogout={handleLogout} />
      {!logueado ? (
        <Login onLogin={handleLogin} />
      ) : (
        <>
          {/* Pantallas de operario */}
          {tipoUsuario === "operario" && (
            <>
              {pantalla === 'cajas' && <CajasDevueltas />}
              {pantalla === 'hielo' && <HieloBarcas />}
              {pantalla === 'hieloClientes' && <HieloClientes />}
              {pantalla === 'resumenHieloBarcasDia' && <ResumenHieloBarcasDia />}
              {pantalla === 'resumenHieloClientesDia' && <ResumenHieloClientesDia />}
              {pantalla === 'resumenCajasDia' && <ResumenCajasDia />}
              

            </>
          )}
          {/* Pantallas de oficina */}
          {tipoUsuario === "oficina" && (
            <>
              {pantalla === 'resumenCajas' && <ResumenCajas />}
              {pantalla === 'resumenHieloClientes' && <ResumenHieloClientes />}
              {pantalla === 'resumenHieloBarca' && <ResumenHieloBarca />}
              {pantalla === 'adminClientes' && <InsertarCliente />}
              {pantalla === 'adminBarcas' && <InsertarBarca />}
              {pantalla === 'eliminarClientes' && <EliminarCliente />}
              {pantalla === 'eliminarBarcas' && <EliminarBarca />}
              {pantalla === 'listarClientes' && <ListadoClientes />}
              {pantalla === 'listarBarcas' && <ListadoBarcas />}

            </>
          )}
        </>
      )}
    </div>
  );
}

export default App;
