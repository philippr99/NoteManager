function deletePostEvent(me)
{
  var postHash = me.getElementsByTagName('p')[0].innerHTML;

  $.ajax({
    type:'POST',
    url:'../backend/deletePost.php',
    data:{'noteID': postHash},
    success: function(data)
    {
      data = JSON.parse(data);

      if(data.result == 'success') $(location).attr('href', '../sites/home.php');

    }
  });
}
