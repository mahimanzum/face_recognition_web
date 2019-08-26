$(document).ready(function() {
   var cache = {};
   $('input[name=roleName]').autocomplete({
      source: function(request, response) {
         var term = request.term;
         if(term in cache) {
            response(cache[term]);
            return;
         }
         $.ajax({
            url: 'roles/roleListAjax.jsp?name=' + request.term,
            data: "",
            dataType: "json",
            type: "POST",
            contentType: "application/json",
            success: function(data) {
               cache[term] = data;
               response(data);
            },
            error: function(response) {},
            failure: function(response) {}
         });
      },
      minLength: 1,
      select: function(e, ui) {
         $("input[name=roleID]").val(ui.item.roleID);
         $("input[name=roleName]").val(ui.item.roleName);
         return false;
      },
   }).autocomplete("instance")._renderItem = function(ul, item) {
      console.log(item.roleName);
      return $("<li>").append("<a>" + item.roleName + "</a>").appendTo(ul);
   };
});

function validate() {
   var f = document.forms[0];
   var ob = f.username;
   if(isEmpty(ob.value)) {
      alert("Please enter username");
      ob.value = "";
      ob.focus();
      return false;
   }
   ob = f.password;
   if(isEmpty(ob.value)) {
      alert("Please enter your password");
      ob.value = "";
      ob.focus();
      return false;
   }
   ob = f.repassword;
   if(isEmpty(ob.value)) {
      alert("Please re-enter your password");
      ob.value = "";
      ob.focus();
      return false;
   }
   //match both password
   if(f.password.value != f.repassword.value) {
      alert("Two passwords do not match!!");
      f.repassword.focus();
      return false;
   }
   selectAllOptions();
   return true;
}

function addLoginIP() {
   var form = document.forms[0];
   val = form.LoginIP.value;
   ob = form.LoginIP;
   if(isEmpty(val)) {
      alert("Please enter a Login IP");
      form.LoginIP.focus();
      return false;
   }
   valid = 1;
   dotCount = 0;
   ip = -1;
   for(i = 0; i < ob.value.length; i++) {
      if(ob.value.charAt(i) == '.') {
         dotCount++;
         if(ip > 255 || ip < 0) {
            valid = 0;
         }
         ip = -1;
      } else if(ob.value.charAt(i) >= '0' && ob.value.charAt(i) <= '9') {
         if(ip == -1) ip = 0;
         ip = ip * 10 + parseInt(ob.value.charAt(i));
      } else {
         valid = 0;
      }
   }
   if(dotCount != 3 || ip > 255 || ip < 0) {
      valid = 0;
   }
   if(valid == 0) {
      alert("Please enter valid IP address");
      ob.value = "";
      ob.focus();
      return false;
   }
   for(i = 0; i < form.loginIPs.length; i++) {
      if(form.loginIPs[i].text == val) {
         alert("IP  " + val + " already added");
         return;
      }
   }
   form.loginIPs.add(new Option(val));
   form.LoginIP.value = '';
   return true;
}

function removeLoginIP() {
   var form = document.forms[0];
   index = form.loginIPs.selectedIndex;
   if(index != -1) {
      form.loginIPs.options.remove(index);
   }
}

function selectAllOptions() {
   var len = document.forms[0].loginIPs.length;
   for(i = 0; i < len; i++) {
      document.forms[0].loginIPs.options[i].selected = true;
   }
   return true;
}



