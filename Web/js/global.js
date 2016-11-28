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

      if(data.error == 'none' && data.action == 'registered') setTimeout(function(){$(location).attr('href', '../index.php?action=registered');},250);//swal("Hallo,","You are now registered!","success");
      else if(data.error == 'name_exists') setTimeout(function(){$(location).attr('href', '../index.php?action=acc_exists');},250);//swal("ERROR","the name is already in use!","error");
      else if(data.error == 'none' && data.action == 'none') setTimeout(function(){$(location).attr('href', '../index.php');},250);
      else if(data.error == 'wrong_pw') setTimeout(function(){$(location).attr('href', '../index.php?action=wrong_pw');},250);
      else if(data.error == 'user_not_exists') setTimeout(function(){$(location).attr('href', '../index.php?action=user_not_exists');},250);

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
