function formatDate(dateString) {
  const options = { year: 'numeric', month: 'long', day: 'numeric' }; // Puedes personalizar este objeto para cambiar el formato
  const date = new Date(dateString);
  return date.toLocaleDateString('es-ES', options); // Cambia 'es-ES' por el locale que prefieras
}

// Uso de la función
const isoDateString = '2023-12-06T15:30:00.000Z';
const friendlyDate = formatDate(isoDateString);
console.log(friendlyDate); // Salida: 6 de diciembre de 2023 (dependiendo del locale)
