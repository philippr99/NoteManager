function onRegisterClick()
{
  swal.withForm({
    title: 'Register an account',
    text: 'Type in your username and password!',
    showCancelButton: true,
    confirmButtonColor: '#2ecc71',
    confirmButtonText: 'Register',
    closeOnConfirm: true,
    formFields: [
        { id: 'username', placeholder:'username' },
        { id: 'password', placeholder:'password' }
    ]
  }, function(isConfirm) {
      console.log(this.swalForm) // json object of input fields!
  });
}

function onLoginClick()
{
  swal.withForm({
    title: 'Login to your account',
    text: 'Type in your username and password!',
    showCancelButton: true,
    confirmButtonColor: '#2ecc71',
    confirmButtonText: 'Login',
    closeOnConfirm: true,
    formFields: [
        { id: 'username', placeholder:'username' },
        { id: 'password', placeholder:'password' }
    ]
  }, function(isConfirm) {
      console.log(this.swalForm) // json object of input fields!
  });
}
