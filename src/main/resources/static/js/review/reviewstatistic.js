$(function (){
    //统计数据
    var Statistic = {}
    for (var i = 1; i <= 28 ; i++) {
        Statistic['EST-'+i] = {};
        Statistic['EST-'+i].count = 0;
        Statistic['EST-'+i].grade = 0;
        Statistic['EST-'+i].oneCount = 0;
        Statistic['EST-'+i].twoCount = 0;
        Statistic['EST-'+i].threeCount = 0;
        Statistic['EST-'+i].fourCount = 0;
        Statistic['EST-'+i].fiveCount = 0;
    }
    var reviewlistjson = $('#reviewData').val();
    var reviewlist = JSON.parse(reviewlistjson);
    for (var i in reviewlist) {
        Statistic[reviewlist[i].itemid].count++;
        Statistic[reviewlist[i].itemid].grade += reviewlist[i].grade;
        switch (reviewlist[i].grade){
            case 1:
                Statistic[reviewlist[i].itemid].oneCount++;
                break;
            case 2:
                Statistic[reviewlist[i].itemid].twoCount++;
                break;
            case 3:
                Statistic[reviewlist[i].itemid].threeCount++;
                break;
            case 4:
                Statistic[reviewlist[i].itemid].fourCount++;
                break;
            case 5:
                Statistic[reviewlist[i].itemid].fiveCount++;
                break;
        }
    }
    for (var i in Statistic) {
        Statistic[i].grade = Math.round(Statistic[i].grade/Statistic[i].count*10)/10;
    }
    console.log(Statistic);


    //建立评论统计表
    var ctx = document.getElementById("singleBarChart1");
    ctx.height = 160;

    var labels = [];
    for (var i = 1; i <= 28 ; i++) {
        labels.push('EST-' + i);
    }

    var Data = [];
    for (var i in Statistic) {
        Data.push(Statistic[i].count);
    }

    var myChart1 = new Chart(ctx, {
        type: 'bar',
        data: {
            labels: labels,
            datasets: [
                {
                    label: '评论数量',
                    data: Data,
                    borderColor: "rgba(117, 113, 249, 0.9)",
                    borderWidth: "0",
                    backgroundColor: "rgba(117, 113, 249, 0.5)"
                }
            ]
        },
        options: {
            scales: {
                y: {
                    beginAtZero: true
                }
            }
        }
    });

    //绑定事件
    $('#select1').on('change', function (){
        var Data = [];
        if($(this).val() == '评论数量') {
            for (var i in Statistic) {
                Data.push(Statistic[i].count);
            }
            myChart1.data.datasets[0].label = '评论数量';
            myChart1.data.datasets[0].data = Data;
        }else if($(this).val() == '平均评分'){
            for (var i in Statistic) {
                Data.push(Statistic[i].grade);
            }
            myChart1.data.datasets[0].label = '平均评分';
            myChart1.data.datasets[0].data = Data;
        }
        myChart1.update();
    });



    //建立评分统计表
    for (var d = 1; d <= 28; d++) {
        $('#select2').append( $('<option style="color: #76838f;font-weight: bold" value="EST-'+d+'">EST-'+d+'</option>') );
    }
    var ctx = document.getElementById("singleBarChart2");
    ctx.height = 160;

    var labels = [];
    for (var i = 5; i >= 1 ; i--) {
        labels.push(i);
    }

    var Data = [];
    for (var i = 1; i <= 5; i++) {
        Data.push(Statistic[$('#select2').val()].fiveCount);
        Data.push(Statistic[$('#select2').val()].fourCount);
        Data.push(Statistic[$('#select2').val()].threeCount);
        Data.push(Statistic[$('#select2').val()].twoCount);
        Data.push(Statistic[$('#select2').val()].oneCount);
    }
    var myChart2 = new Chart(ctx, {

        type: 'bar',
        data: {
            labels: labels,
            datasets: [
                {
                    axis: 'y',
                    label: "数量",
                    data: Data,
                    borderColor: "rgba(117, 113, 249, 0.9)",
                    borderWidth: "0",
                    backgroundColor: "rgba(117, 113, 249, 0.5)"
                }
            ]
        },
        options: {
            indexAxis: 'y',
        }
    });
    //绑定事件
    $('#select2').on('change',function (){
        var Data = [];
        for (var i = 1; i <= 5; i++) {
            Data.push(Statistic[$('#select2').val()].fiveCount);
            Data.push(Statistic[$('#select2').val()].fourCount);
            Data.push(Statistic[$('#select2').val()].threeCount);
            Data.push(Statistic[$('#select2').val()].twoCount);
            Data.push(Statistic[$('#select2').val()].oneCount);
        }
        myChart2.data.datasets[0].data = Data;
        myChart2.update();
    })
})