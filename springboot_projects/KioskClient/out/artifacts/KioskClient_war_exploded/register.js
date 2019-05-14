var url="http://localhost:9098/api/addStudent";
function test() {
    var id=parseInt($("#inpId").val());
    var name=$("#inpName").val();
    var age=parseInt($("#inpAge").val());
    var gender=$("#inpGender").val();
    var obj={};
    obj.id=id;
    obj.name=name;
    obj.age=age;
    obj.gender=gender;
    POST(obj);
}

function POST(obj){
     $.ajax({
        type: "POST",
        url: url,
        data: JSON.stringify(obj),
        success: function (result) {
            alert("Registered");
            console.log(result);
        },
        contentType: 'application/json',
    });
}

