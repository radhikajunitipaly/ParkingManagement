<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript">
function editmode()
{
document.getElementById("edit").style.display='none';
var capacity = document.getElementsByClassName("capacity");
var type = document.getElementsByClassName("type");
var update = document.getElementsByClassName("update");
length = capacity.length;
for(var i=0;i<length;i++)
	{
	type[i].disabled = false;
	capacity[i].disabled = false;
	update[i].style.display='block';
	}
}

function display_edit()
{
	var check = ${onloads};
	if(check == 1)
	document.getElementById("edit").style.display='block';
}

function display_add()
{
document.getElementById("add_parking").style.display = 'block';
document.getElementById("modifyparkingarea").style.display = 'none';
document.getElementById("change_name").style.display = 'none';
}

function display_modify()
{
	document.getElementById("add_parking").style.display = 'none';
	document.getElementById("modifyparkingarea").style.display = 'block';
	document.getElementById("change_name").style.display = 'none';
}
function display_namechange()
{
	document.getElementById("add_parking").style.display = 'none';
	document.getElementById("modifyparkingarea").style.display = 'none';
	document.getElementById("change_name").style.display = 'block';	
}
</script>
</head>
<body onload="display_edit();">
<button style="margin-left:50px;" onclick="display_add();">Add Parking Area</button><button style="margin-left:50px;" onclick="display_modify();">Modify Parking Area</button><button style="margin-left:50px;" onclick="display_namechange();">Change Name of Parking Area</button>
<div id="add_parking" style="display:none;">
<form action="parkingspotController?action=addparking" method="post">
<table>
<tr><td>Parking Area Name:</td><td><input type="text" name="park_name"></td><tr>
<tr><td>Floor</td><td><input type="text" name="park_floor"></td><tr>
<tr><td>Capacity</td><td><input type="text" name="park_cap"></td><tr>
<tr><td>Type:</td><td><select name="park_type"><option>Basic<option>Premium<option>Midrange<option>Access</select></td></tr>
<tr><td><input type="submit" value="Add">
</table>
</form>
</div>
<div id="change_name" style="display:none;">
<form action="parkingspotController?action=changename" method="post" onsubmit="return confirm('Confirmation required');" >
<table>
<tr><td>Parking Area Name:</td><td><select name="oldname" onchange="callnames();">
<c:forEach items="${modifyparkingAreaNames}" var="parkingareaname">
<option value='${parkingareaname }'>${parkingareaname }</option>
</c:forEach>
</select></td></tr>
<tr><td>New Name:</td><td><input type="text" name="newname"/></td></tr>
<tr><td><input  type="submit" value="Change" ></td></tr> 
</table>
</form>
</div>
<div id="modifyparkingarea" style="display:none;">
<form action="parkingspotController?action=modifyparkingareas" method="post">
<table>
<tr><td>Parking Area Name:</td><td><select name="parkingareaname" onchange="callnames();">
<c:forEach items="${modifyparkingAreaNames}" var="parkingareaname">
<option value='${parkingareaname }'>${parkingareaname }</option>
</c:forEach>
</select></td></tr>
<tr><td><input  type="submit" value="Search" ></td></tr> 
</table>
</form>
</div>

<c:forEach items="${parkinfo}" var="modifypark">
<form action="parkingspotController?action=updateparkingarea&parkingname=${modifypark.parkingarea_name}" method="post">
<table>
<tr><td>Floor:</td><td><input name="floor" type="text" value='${modifypark.floor}' READONLY></td><td>Capacity:</td><td><input name="capacity" class="capacity" type="text" value='${modifypark.capacity}' disabled></td><td>Type:</td><td><input name="type" type="text" class="type" value='${modifypark.parkingtype}' disabled></td><td><input class="update" type="submit" value="Update" style="display:none;"></td></tr>
</table>
</form>
</c:forEach>
<button id="edit" onclick="editmode();" style="display:none;">Edit</button>
</body>
</html>