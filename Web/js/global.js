function sendPostRequestLogin(isLogin, username,password)
{
  $.ajax({
    type:'POST',
    url:'../backend/register_login.php',
    data:{'isLogin': isLogin, 'username':username,'password':password},
    success: function(data)
    {
      data = JSON.parse(data);

      console.log('here');
      console.log(data);

      if(data.error == 'none' && data.action == 'registered') $(location).attr('href', '../index.php?action=registered');//swal("Hallo,","You are now registered!","success");
      else if(data.error == 'name_exists') $(location).attr('href', '../index.php?action=acc_exists');//swal("ERROR","the name is already in use!","error");
      else if(data.error == 'none' && data.action == 'none') $(location).attr('href', '../index.php');
    }
  });
}

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
      if(isConfirm)
        sendPostRequestLogin(0,this.swalForm.username,this.swalForm.password);
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
    if(isConfirm)
      sendPostRequestLogin(1,this.swalForm.username,this.swalForm.password);
  });
}
