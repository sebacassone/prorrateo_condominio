export function validarRUT(rut) {
    // Limpiar el RUT dejando solo números y el dígito verificador
    if (rut.includes('.') || !rut.includes('-')) {
        return false;
    }
    if (rut.length > 10 && rut.length < 9) {
        return false;
    }
    var valor = rut.replace(/\./g, '').replace('-', '');
  
    // Dividir el cuerpo y el dígito verificador
    var cuerpo = valor.slice(0, -1);
    var dv = valor.slice(-1).toUpperCase();
  
    // Formatear el RUT (con puntos y guión)
    rut = cuerpo + '-' + dv
  
    // Si no cumple con el mínimo de dígitos o una expresión regular básica, invalidar
    if (cuerpo.length < 7 || !/^0*(\d{1,3}(\.?\d{3})*)\-?([\dkK])$/.test(rut)) {
      return false;
    }
  
    // Calcular el dígito verificador
    var suma = 0;
    var multiplo = 2;
  
    // Para cada dígito del cuerpo del RUT
    for (var i = 1; i <= cuerpo.length; i++) {
      // Obtener el dígito correspondiente del cuerpo
      var index = multiplo * valor.charAt(cuerpo.length - i);
      
      // Sumar al contador general
      suma = suma + index;
      
      // Actualizar el múltiplo
      if (multiplo < 7) {
        multiplo = multiplo + 1;
      } else {
        multiplo = 2;
      }
    }
  
    // Calcular dígito verificador en base al módulo 11
    var dvEsperado = 11 - (suma % 11);
    
    // Casos especiales (0 y K)
    dv = (dv == 'K') ? 10 : dv;
    dv = (dv == 0) ? 11 : dv;
  
    // El dígito verificador debe ser igual al último dígito del RUT
    if (dvEsperado != dv) {
      return false;
    }
  
    // Si todo está correcto, retornar verdadero
    return true;
  }

 
export function formatLoginResponse(infoString) {
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

export function formatoNumerico(cadena) {
  return /^[0-9]+$/.test(cadena);
}
