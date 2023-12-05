function formatLoginResponse(infoString) {
    // Eliminar los caracteres '{' y '}' y dividir por comas
    let partes = infoString.replace(/[{}]/g, '').split(', ');
  
    let datos = {};
  
    // Recorrer cada parte y asignar valores al objeto datos
    partes.forEach((parte) => {
      let [clave, valor] = parte.split('-');
      datos[clave] = valor;
    });
  
    return datos;
  }
  
// Usar la función con el string proporcionado 
let resultado = formatLoginResponse('{tipoUsuario-1, nombre-Juan}');

console.log('Tipo de Usuario:', resultado.tipoUsuario); // Imprime "Tipo de Usuario: 1"
console.log('Nombre:', resultado.nombre); // Imprime "Nombre: Juan"
  