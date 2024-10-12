
/* create new roject */
function addProject(){
	document.getElementById("addProject").style.display = "flex";
	document.getElementById("indexContainer").style.position = "absolute";
}

/* remove add project interface */
function stopAddProject(){
	document.getElementById("addProject").style.display = "none";
	document.getElementById("indexContainer").style.position = "relative";
	document.getElementById("stillAddingroject").value == "0";
}

function updateProject(event,id) {
	event.preventDefault();
	var divForm = document.getElementById("id"+id);
	divForm.style.display = "flex";
	document.getElementById("indexContainer").style.position = "absolute";
}

function stopModifyingProject(id){
	document.getElementById("id"+id).style.display = "none";
	document.getElementById("indexContainer").style.position = "relative";
}

/* confirmation de suppression */
function confirmDeleting(event){
	if(!confirm("Voulez-vous vraiment supprimer ce projet ?"))
		event.preventDefault();
}

// vreification des champs ajouter project
