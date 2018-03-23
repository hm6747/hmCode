<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<jsp:include page="/static/jsp/common_quote.jsp"/>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>商品管理</title>
</head>

<body class="gray-bg">
<div class="wrapper wrapper-content  animated fadeInRight">
    <div class="row">
        <div class="col-sm-12">
            <div class="ibox">
                <div class="ibox-title">
                    <h5>商品列表</h5>
                    <div class="ibox-content">
                        <div class="row m-b-sm m-t-sm">
                            <div class="col-md-12">
                                <div class="input-group">
                                    <input type="text" placeholder="查找商品" class="input form-control" id="keyword">
                            <span class="input-group-btn">
                                        <button type="button" class="btn btn btn-primary" id="search"><i
                                                class="fa fa-search"></i>
                                            搜索
                                        </button>
                                </span>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="clients-list">
                        <ul class="nav nav-tabs">
                            <span class="pull-right small text-muted">总共<span id="total"></span>个商品</span>
                            <div class="ibox-tools" style="float: right">
                                <a onclick="window.location.href=path+'/admin/item/add.page'" class="btn btn-primary btn-xs" ><i class="fa fa-plus"></i>添加商品</a>
                            </div>
                            <li class="active"><a data-toggle="tab" href="#tab-1"><i class="fa fa-user"></i> 商品</a>
                            </li>
                            <%--        <li class=""><a data-toggle="tab" href="#tab-2"><i class="fa fa-briefcase"></i> 部门</a>
                                    </li>--%>
                        </ul>
                        <div class="tab-content">
                            <div id="tab-1" class="tab-pane active">
                                <div class="full-height-scroll">
                                    <div class="table-responsive">
                                        <table class="table table-striped table-hover" style="table-layout: fixed">
                                            <thead>
                                            <tr>
                                                <th class="table-title">序号</th>
                                                <th class="table-title"
                                                    style="overflow:hidden;white-space:nowrap;text-overflow:ellipsis;">
                                                    标题
                                                </th>
                                                <th class="table-type">价格</th>
                                                <th class="table-num">库存</th>
                                                <th class="table-type">状态</th>
                                                <th class="table-type ">类目</th>
                                                <th class="table-type ">条形码</th>
                                                <th class="table-type "
                                                    >
                                                    卖点
                                                </th>
                                                <th class="table-date "
                                                    >
                                                    创建日期
                                                </th>
                                                <th class="table-date "
                                                    >
                                                    更新日期
                                                </th>
                                                <th class="table-set">操作</th>
                                            </tr>
                                            </thead>
                                            <tbody id="listTable">
                                            </tbody>
                                        </table>
                                        <div class="btn-group showPages">
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script id="listTemplate" type="x-tmpl-mustache">
{{#itemList}}
<tr>
    <td>{{id}}</td>
    <td style="overflow:hidden;white-space:nowrap;text-overflow:ellipsis;"><a href="#">{{title}}</a></td>
    <td>{{price}}</td>
    <td>{{num}}</td>
        <td class="client-status"><span class="label {{statusClass}}">{{showStatus}}</span>
    </td>
    <td >{{cid}}</td>
    <td >{{id}}</td>
    <td  style="overflow:hidden;white-space:nowrap;text-overflow:ellipsis;">{{sellPoint}}</td>
    <td class="formatData" data-formatStr="yyyy/MM/dd hh:mm:ss" style="overflow:hidden;white-space:nowrap;text-overflow:ellipsis;">{{updated}}</td>
    <td class=" formatData" data-formatStr="yyyy/MM/dd hh:mm:ss" style="overflow:hidden;white-space:nowrap;text-overflow:ellipsis;">{{updated}}</td>
    <td>
                          <a  class="btn btn-white btn-sm editRole" data-toggle="modal" data-target="#roleModal"
                                        data-Id="{{id}}"><i class="fa fa-pencil"></i> 编辑 </a>
    </td>
</tr>
{{/itemList}}



</script>
<script type="application/javascript">
    $(function () {
        $("#search").on("click", function () {
            search();
        })
        loadList();
    })
    function search() {
        var keyWord = $("#keyword").val();
        loadList(keyWord,true);
    }
    function loadList(keyWord,flag) {
        var url = "/admin/item/list.json";
        var data = {
            keyWord: keyWord
        }
        var listJson = {
            "itemList": "",
            "statusClass": function () {
                return this.status == 1 ? "label-primary" : this.status == 0 ? "label-warning" : "label-danger";
            },
            "showStatus": function () {
                return this.status == 1 ? "正常" : this.status == 2 ? "下架" : "删除";
            }
        };
        var listTemplate = $("#listTemplate").html();
        var successCallback = function (result) {
            createMustache(listJson, result, listTemplate, $("#listTable"));
            $("#total").html(result.data.total)
/*            bindClik();*/
            formatData();
        }
        getTableList(url, data, true, successCallback,null,flag);
    }
</script>
</body>

</html>
