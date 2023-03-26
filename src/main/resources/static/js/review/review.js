$(function (){
    //置顶按钮
    $(".btn-outline-primary,.btn-primary").each(function (){
        console.log($(this).next().next().text());
        $(this).on('click', function (){
            var el = $(this);
            var id = $(this).next().next().text();

            if(el.text() == "置顶"){
                el.text("已置顶");
                el.removeClass("btn-outline-primary");
                el.addClass("btn-primary");
                $.ajax({
                    type: 'GET',
                    url: 'reviewtop?id=' + id,
                    success: function (data) {
                        if(data != 0){
                            var elt = $("#" + data).prev();
                            elt.text("置顶");
                            elt.removeClass("btn-primary");
                            elt.addClass("btn-outline-primary");
                        }
                    }
                })
            }
            else{
                $.ajax({
                    type: 'GET',
                    url: 'canceltop?id=' + id,
                    success: function () {
                        el.text("置顶");
                        el.removeClass("btn-primary");
                        el.addClass("btn-outline-primary");
                    }
                })
            }

        })
    })

    //删除按钮
    $(".btn-outline-danger").each(function (){
        $(this).on('click', function (){
            var id = $(this).next().text();
            var el = $(this);
            console.log(id);
            $.ajax({
                type: 'GET',
                url: 'reviewdelete?id=' + id,
                success: function (data){
                    console.log("111");
                    var Row = el.parent().parent().parent();
                    table.row(Row).remove();
                    Row.remove();
                }
            })
        })
    })

    //初始化表格
    var table = $('table').DataTable({
        paging: false,
        scrollY: 550,
        dom:'<fi<"#toolbox">t>',
        initComplete:function(){
            $("#toolbox").append("<br><h5 style='display:inline;padding-left:40px;color:#76838f'>商品：  </h5><select/ id='column1_search' style=\"color: #76838f;font-weight: bold;width: 100px;display: inline\">");
            $("#toolbox").append("<h5 style='display:inline;padding-left:40px;color:#76838f'>评分：  </h5><select/ id='column2_search' style=\"color: #76838f;font-weight: bold;width: 100px;margin-bottom: 20px\">");
        },
        columns: [
            null,
            null,
            null,
            { "width": "20%", "orderable" : false },
            null,
            {"orderable" : false}
        ]
    });

    //设置筛选框
    var column1 = table.column(1);
    var select = $('#column1_search');
    select.on( 'change', function () {
        if($(this).val() == '全部')
            column1.search('').draw();
        else
            column1.search($(this).val()+'$', true).draw();
    } );
    select.append( $('<option value="全部" style="color: #76838f;font-weight: bold">全部</option>') );
    for (var d = 1; d <= 28; d++) {
        select.append( $('<option style="color: #76838f;font-weight: bold" value="EST-'+d+'">EST-'+d+'</option>') );
    }

    var column2 = table.column(2);
    var select = $('#column2_search');
    select.on( 'change', function () {
        if($(this).val() == '全部')
            column2.search('').draw();
        else
            column2.search( $(this).val() ).draw();
    } );
    select.append( $('<option value="全部" style="color: #76838f;font-weight: bold">全部</option>') );
    for (var d = 5; d >= 1; d--) {
        select.append( $('<option style="color: #76838f;font-weight: bold" value="'+d+'" >'+d+'</option>') );
    }
})