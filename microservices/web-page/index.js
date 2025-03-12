


// sync api call

const xhr = new XMLHttpRequest();
xhr.open('GET', 'http://localhost:8181/product-composite/1', true); // No-blocking
xhr.send();
xhr.onreadystatechange = function() {
  if (xhr.readyState === 4) {
    if (xhr.status === 200) {
    document.getElementById('product').innerHTML = xhr.responseText;
    } else {
      console.error(xhr.statusText);
    }
  }
};