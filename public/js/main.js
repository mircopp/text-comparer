/**
 * Created by Mirco on 01.10.2016.
 */

 function getSimilarity(){
    var text1 = $('#text1').val();
    var text2 = $('#text2').val();
    var url = 'http://localhost:5000/getSimilarity';

    var data = {
        "text1": text1,
        "text2": text2
    }

    $.ajax({url: url, method:'POST', contentType:"application/json", data: JSON.stringify(data), success: function(res){
            var response = eval(res);
            $('#result').css('display', 'block');
            $('#result').html(response.similarity);
        }
    });
 }
