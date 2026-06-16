(function () {
  ClassicEditor.create(document.querySelector('#inputContent'), {
      toolbar: {
        items: [
          'heading',
          '|',
          'bold',
          'italic',
          'link',
          'bulletedList',
          'numberedList',
          '|',
          'outdent',
          'indent',
          '|',
          'blockQuote',
          'insertTable',
          'mediaEmbed',
          'undo',
          'redo'
        ]
      },
      language: 'ko',
      table: {
        contentToolbar: [
          'tableColumn',
          'tableRow',
          'mergeTableCells'
        ]
      },
      licenseKey: '',
    })
    .then(editor => {
      window.editor = editor;
    })
    .catch(error => {
      console.error('Oops, something went wrong!');
      console.error('Please, report the following error on https://github.com/ckeditor/ckeditor5/issues with the build id and the error stack trace:');
      console.warn('Build id: 5y8b948gyt5-jfha1cexgplv');
      console.error(error);
    });
})()