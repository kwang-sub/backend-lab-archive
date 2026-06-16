(function () {
  'use strict'

  const forms = document.querySelectorAll('.needs-validation')
  Array.prototype.slice.call(forms)
    .forEach(function (form) {
      form.addEventListener('submit', function (event) {
        if (!form.checkValidity()) {
          event.preventDefault()
          event.stopPropagation()
        }
        form.classList.add('was-validated')
      }, false)
    })
})()


const imageInput = document.querySelectorAll('.image-input');
imageInput.forEach(function(item, index) {
  item.addEventListener('change', function(event) {
    const preview = document.getElementById('image-preview' + index);
    preview.style.backgroundImage = "url('" + URL.createObjectURL(this.files[0]) + "')"
    preview.addEventListener('onload', function() {
      URL.revokeObjectURL(preview.style.backgroundImage);
    })
  });
})