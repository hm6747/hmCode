<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<jsp:include page="/common/common_quote.jsp"/>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>部门管理</title>
</head>

<body class="gray-bg">
<div class="wrapper wrapper-content  animated fadeInRight">
    <div class="row">
        <div class="col-sm-4">
            <div class="ibox ">

                <div class="ibox-content">
                    <%--        <span class="text-muted small pull-right">最后更新：<i class="fa fa-clock-o"></i> 2015-09-01 12:00</span>--%>
                    <h2>部门管理</h2>
                    <div class="ibox-tools">
                        <a onclick="addDpet()" data-toggle="modal" data-target="#deptModel"
                           class="btn btn-primary btn-xs" userModel><i class="fa fa-plus"></i>创建新部门</a>
                    </div>
                    <p>
                        所有部门必须状态正常
                    </p>
                    <div id="deptTree" class="dd nestable2"></div>
                    <script id="deptListTemplate" type="x-tmpl-mustache">
                    <ol class="dd-list">
                     {{#deptTreeList}}
                        <li class="dd-item dept-name" data-id="{{id}}" id="dept_{{id}}">
                        <div class="dd-handle dd-content" style="text-align: left;">
                            <span class="label label-info"><i class="fa fa-users"></i></span> {{name}}
                          <a  class="fa-hover delDept" style="float:right;margin-right: 20px"  data-id="{{id}}"><i class="fa fa-trash-o"></i></a>
                           <a class="fa-hover editDept" style="float:right;margin-right: 10px" data-toggle="modal" data-target="#deptModel" data-id="{{id}}"><i class="fa fa-edit"></i></a>
                        </div>
                        </li>
                     {{/deptTreeList}}
                     </ol>



                    </script>
                </div>
            </div>
        </div>
        <div class="col-sm-8">
            <div class="ibox">
                <div class="ibox-content">
                    <%--     <span class="text-muted small pull-right">最后更新：<i class="fa fa-clock-o"></i> 2015-09-01 12:00</span>--%>
                    <h2>用户管理</h2>
                    <p>
                        所有用户必须状态正常
                    </p>
                    <div class="input-group">
                        <input type="text" placeholder="查找用户(支持姓名、邮箱、手机号模糊查询)" class="input form-control" id="keyword">
                            <span class="input-group-btn">
                                        <button type="button" class="btn btn btn-primary" id="search"><i
                                                class="fa fa-search"></i>
                                            搜索
                                        </button>
                                </span>
                    </div>
                    <div class="clients-list">
                        <ul class="nav nav-tabs">
                            <span class="pull-right small text-muted">总共<span id="total"></span>个用户</span>
                            <div class="ibox-tools" style="float: right">
                                <a onclick="addUser()" class="btn btn-primary btn-xs" data-toggle="modal"
                                   data-target="#userModel"><i class="fa fa-plus"></i>创建新用户</a>
                            </div>
                            <li class="active"><a data-toggle="tab" href="#tab-1"><i class="fa fa-user"></i> 用户</a>
                            </li>
                            <%--        <li class=""><a data-toggle="tab" href="#tab-2"><i class="fa fa-briefcase"></i> 部门</a>
                                    </li>--%>
                        </ul>
                        <div class="tab-content">
                            <div id="tab-1" class="tab-pane active">
                                <div class="full-height-scroll">
                                    <div class="table-responsive">
                                        <table class="table table-striped table-hover">
                                            <thead>
                                            <tr>
                                                <th class="table-id">头像</th>
                                                <th class="table-title">姓名</th>
                                                <th class="table-type">部门</th>
                                                <th class="table-num">邮箱</th>
                                                <th class="table-type ">电话</th>
                                                <th class="table-type ">状态</th>
                                                <th class="table-set">操作</th>
                                            </tr>
                                            </thead>
                                            <tbody id="listTable">
                                            </tbody>
                                        </table>
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
<div class="modal inmodal" id="deptModel" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content animated fadeIn">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span
                        class="sr-only">Close</span></button>
                <h4 class="modal-title" id="addDeptTitle">添加部门</h4>
                <%--     <small>这里可以显示副标题。--%>
            </div>
            <form method="get" class="form-horizontal" id="deptForm">
                <div class="col-md-12 modal-body">
                    <div class="form-group">
                        <label class="col-sm-2 control-label">上级部门</label>
                        <div class="col-sm-10">
                            <button type="button" class="btn btn-primary"
                                    id="addDeptBtn">
                                选择部门
                            </button>
                            <button type="button" class="btn btn-primary"
                                    id="resetDeptBtn">
                                清空部门
                            </button>
                            <span style="margin-left: 20px;" id="deptName"></span>
                            <input readonly name="id" type="hidden" id="deptId">
                            <input readonly name="parentId" type="hidden" id="parentId" value="0">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label"></label>
                        <div id="addDeptTree" class="dd nestable2 col-sm-10" style="display: none"></div>
                        <script id="addDeptListTemplate" type="x-tmpl-mustache">
                                                        <ol class="dd-list">
                                                                {{#addDeptTreeList}}
                                                                <li class="dd-item dept-name" data-id="{{id}}" id="addDept_{{id}}">
                                                                <div class="dd-handle dd-content" style="text-align: left;">
                                                                    <span class="label label-info"><i class="fa fa-users"></i></span> {{name}}
                                                                </div>
                                                                </li>
                                                             {{/addDeptTreeList}}
                                                         </ol>



                        </script>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">名称</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" aria-required="true" required name="name"
                                   id="addDeptDeptName">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">顺序</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" name="seq" id="addDeptSeq" value="">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">备注</label>
                        <div class="col-sm-10">
                            <textarea name="remark" id="addDeptRemark" class="form-control" required=""
                                      aria-required="true"></textarea>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-white closeModel" data-dismiss="modal">关闭</button>
                    <button type="submit" class="btn btn-primary">保存</button>
                </div>
            </form>
        </div>
    </div>
</div>

<div class="modal inmodal" id="userModel" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content animated fadeIn">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span
                        class="sr-only">Close</span></button>
                <h4 class="modal-title" id="addUserTitle">添加用户</h4>
                <%--     <small>这里可以显示副标题。--%>
            </div>
            <form method="get" class="form-horizontal" id="userForm">
                <div class="col-md-12 modal-body">
                    <div class="form-group">
                        <label class="col-sm-2 control-label">所在部门</label>
                        <div class="col-sm-10">
                            <button type="button" class="btn btn-primary"
                                    id="addUserBtn">
                                选择部门
                            </button>
                            <span style="margin-left: 20px;" id="addDeptName"></span>
                            <input readonly name="deptId" type="hidden" id="addDdeptId">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label"></label>
                        <div id="addUserDeptTree" class="dd nestable2 col-sm-10" style="display: none"></div>
                        <script id="addUserDeptListTemplate" type="x-tmpl-mustache">
                                                        <ol class="dd-list">
                                                                {{#addUserDeptTreeList}}
                                                                <li class="dd-item dept-name" data-id="{{id}}" id="addUserDept_{{id}}">
                                                                <div class="dd-handle dd-content" style="text-align: left;">
                                                                    <span class="label label-info"><i class="fa fa-users"></i></span> {{name}}
                                                                </div>
                                                                </li>
                                                             {{/addUserDeptTreeList}}
                                                         </ol>



                        </script>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">名称</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" aria-required="true" required id="addUserName"
                                   name="username">
                            <input type="hidden" name="id" id="userId"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">邮箱</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" name="mail" id="addUserMail" value="">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">电话</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" name="telephone" id="addUserTelephone" value="">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">状态</label>
                        <div class="col-sm-10">
                            <select class="form-control m-b" id="addUserStatus" name="status" data-placeholder="选择状态">
                                <option value="1">有效</option>
                                <option value="0">无效</option>
                                <option value="2">冻结</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">备注</label>
                        <div class="col-sm-10">
                            <textarea name="remark" id="addUserRemark" class="form-control" required=""
                                      aria-required="true"></textarea>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-white closeModel" data-dismiss="modal">关闭</button>
                    <button type="submit" class="btn btn-primary">保存</button>
                </div>
            </form>
        </div>
    </div>
</div>
<div class="modal inmodal" id="roleUserModal" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content animated fadeIn">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span
                        aria-hidden="true">&times;</span><span
                        class="sr-only">Close</span></button>
                <h4 class="modal-title">分配角色</h4>
                <%--     <small>这里可以显示副标题。--%>
            </div>
            <form method="get" class="form-horizontal" id="roleUserForm">
                <input type="hidden" id="updateUserRoleId">
                <div class="col-md-12 modal-body">
                    <div class="form-group">
                        <ul id="roleUserTree" class="ztree"></ul>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-white closeModel" data-dismiss="modal">关闭</button>
                    <button type="button" class="btn btn-primary" id="roleUserUpdate">保存</button>
                </div>
            </form>
        </div>
    </div>
</div>
<script id="listTemplate" type="x-tmpl-mustache">
{{#userList}}
<tr>
    <td class="client-avatar"><img alt="image" src="${pageContext.request.contextPath}/img/a2.jpg"> </td>
    <td><a data-toggle="tab" href="#contact-1" class="client-link">{{username}}</a>
    </td>
    <td> {{showDeptName}}</td>
    <td >
    {{mail}}
    </td>
    <td>  {{telephone}}</td>
    <td class="client-status"><span class="label {{statusClass}}">{{showStatus}}</span>
    </td>
    <td>
                          <a  class="btn btn-white btn-sm editUser" data-toggle="modal" data-target="#userModel"
                                        data-Id="{{id}}" data-deptId="{{deptId}}"><i class="fa fa-pencil"></i> 编辑 </a>
                     <a class="btn btn-white btn-sm"><i class="fa fa-folder"></i> 删除 </a>
                    <a class="btn btn-white btn-sm userRole" data-Id="{{id}}" data-toggle="modal" data-target="#roleUserModal"><i class="fa fa-user" ></i> 角色 </a>
    </td>
</tr>
{{/userList}}




</script>
<script>
    var deptTreeList; //缓存树形目录
    var deptTreeMap = {};
    var userMap = {};
    var lastClickDeptId = -1;
    $(document).ready(function () {
        itemTreeCreate();
        $("#search").on("click", function () {
            searchUser();
        })
        binAddUserBtn();
        commonValidate("userForm", updateUser);
        commonValidate("deptForm", updateDept);
        $("#roleUserUpdate").on("click",function () {
            updateRoleUser();
        })
    });
    function addUser() {
        $("#userForm")[0].reset();
        $("#addUserTitle").html("添加用户");
        $("#addDeptName").html("");
        $("#userId").val("");
        $("#addDdeptId").val("");
    }
    function updateDept() {
        var url = (!$("#deptId").val()) ? "/sys/dept/save.json" : "/sys/dept/update.json";
        var data = $("#deptForm").serializeArray();
        if ($("#deptId").val()) {
            submitAjaxForm(url, data, function (result) {
                console.log(result)
                itemTreeCreate();
                $(".closeModel").click();
            }, function (result) {
                console.log(result)
            });
        } else {
            submitAjaxForm(url, data, function (result) {
                itemTreeCreate();
                $(".closeModel").click();
                console.log(result)
            }, function (result) {
                console.log(result)
            });
        }
    }
    function updateUser() {
        var url = (!$("#userId").val()) ? "/sys/user/save.json" : "/sys/user/update.json";
        var data = $("#userForm").serializeArray();
        if ($("#userId").val()) {
            submitAjaxForm(url, data, function (result) {
                console.log(result)
                $(".closeModel").click();
                searchUser();
            }, function (result) {
                console.log(result)
            });
        } else {
            submitAjaxForm(url, data, function (result) {
                $(".closeModel").click();
                console.log(result)
            }, function (result) {
                console.log(result)
            });
        }
    }
    function binAddUserBtn() {
        $("#addUserBtn").on("click", function () {
            if ($(this).html().trim() == "选择部门") {
                $("#addUserDeptTree").show();
                $(this).html("收起部门");
            } else {
                $("#addUserDeptTree").hide();
                $(this).html("选择部门");
            }
        })
        $("#addDeptBtn").on("click", function () {
            if ($(this).html().trim() == "选择部门") {
                $("#addDeptTree").show();
                $(this).html("收起部门");
            } else {
                $("#addDeptTree").hide();
                $(this).html("选择部门");
            }
        })
        $("#resetDeptBtn").on("click", function () {
            $("#parentId").val("0");
            $("#deptName").html("");
        })
    }
    function searchUser() {
        var keyword = $("#keyword").val();
        loadUserList(lastClickDeptId, keyword);
    }
    function itemTreeCreate() {
        var url = "/sys/dept/tree.json";
        loadDpetTree(url);
    }
    var nesBindEvent = function () {
        $("#deptTree .dd-handle").click(function () {
            var deptId = $(this).parent().attr("data-id");
            handleDeptSelected(deptId, this);
        })
    }
    var nesBindEventAddUser = function () {
        $("#addUserDeptTree .dd-handle").click(function () {
            var deptId = $(this).parent().attr("data-id");
            $("#addDeptName").html(deptTreeMap[deptId].name);
            $("#addDdeptId").val(deptId);
        })
    }
    var nesBindEventAddDept = function () {
        $("#addDeptTree .dd-handle").click(function () {
            var deptId = $(this).parent().attr("data-id");
            $("#deptName").html(deptTreeMap[deptId].name);
            $("#parentId").val(deptId);
        })
    }
    function handleDeptSelected(deptId, ele) {
        if (deptId == lastClickDeptId) {
            $(ele).removeClass("nestable2Selected").addClass("dd-handle");
            lastClickDeptId = -1;
            loadUserList(lastClickDeptId)
            return false;
        } else {
            if (lastClickDeptId != -1 && deptId != lastClickDeptId) {
                var lastDept = $("#dept_" + lastClickDeptId + " .dd-content:first");
                lastDept.removeClass("nestable2Selected").addClass("dd-handle");
            }
        }
        lastClickDeptId = deptId;
        $(ele).removeClass("dd-handle").addClass("nestable2Selected");
        loadUserList(lastClickDeptId)
    }
    function loadUserList(deptId, keyword) {
        var url = "/sys/user/list.json";
        var data = {
            deptId: deptId,
            keyword: keyword
        }
        var listJson = {
            "userList": "",
            "showStatus": function () {
                return this.status == 1 ? "有效" : (this.status == 0 ? "无效" : "冻结");
            },
            "statusClass": function () {
                return this.status == 1 ? "label-primary" : this.status == 0 ? "label-warning" : "label-danger";
            },
            "showDeptName": function () {
                return deptTreeMap[this.deptId].name;
            }
        };
        var listTemplate = $("#listTemplate").html();
        var successCallback = function (result) {
            createMustache(listJson, result, listTemplate, $("#listTable"));
            createListMap(result, userMap);
            $("#total").html(result.data.total);
            bindEditUserClik();
            formatData();
        }
        getTableList(url, data, false, successCallback);
    }
    function bindEditUserClik() {
        $(".editUser").on("click", function () {
            $("#addUserTitle").html("编辑用户");
            var targetUser = userMap[$(this).attr("data-Id")];
            if (targetUser) {
                $("#addDdeptId").val(targetUser.deptId);
                $("#addDeptName").html(deptTreeMap[targetUser.deptId].name);
                $("#addUserName").val(targetUser.username);
                $("#addUserMail").val(targetUser.mail);
                $("#addUserTelephone").val(targetUser.telephone);
                $("#addUserStatus").val(targetUser.status);
                $("#addUserRemark").val(targetUser.remark);
                $("#userId").val(targetUser.id);
            }
        })
        $(".userRole").on("click",function () {
            createUserRoleTree($(this).attr("data-Id"));
        })
    }
    function bindEditDeptClik() {
        $(".editDept").on("click", function (e) {
            $("#addDeptTitle").html("编辑部门");
            var targetDept = deptTreeMap[$(this).attr("data-id")];
            if (targetDept) {
                $("#deptId").val(targetDept.id);
                $("#parentId").val(targetDept.parentId);
                $("#deptName").html(targetDept.parentId != 0 ? deptTreeMap[targetDept.parentId].name : "");
                $("#addDeptDeptName").val(targetDept.name);
                $("#addDeptSeq").val(targetDept.seq);
                $("#addDeptRemark").val(targetDept.remark);
            }
        })
    }
    function loadDpetTree(url) {
        $.ajax({
            url: path + url,
            success: function (result) {
                debugger;
                deptTreeList = result.data;
                var a = deptTreeList;
                var deptListTemplate = $("#deptListTemplate").html();
                var addUserDeptListTemplate = $("#addUserDeptListTemplate").html();
                var addDeptListTemplate = $("#addDeptListTemplate").html();
                createMustacheTree(deptTreeList, addDeptListTemplate, {addDeptTreeList: deptTreeList}, $("#addDeptTree"), "#addDept_", nesBindEventAddDept);
                createMustacheTree(a, addUserDeptListTemplate, {addUserDeptTreeList: a}, $("#addUserDeptTree"), "#addUserDept_", nesBindEventAddUser);
                createMustacheTree(deptTreeList, deptListTemplate, {deptTreeList: deptTreeList}, $("#deptTree"), "#dept_", nesBindEvent);
                loadUserList(lastClickDeptId);
                bindEditDeptClik();
            }
        })
    }
    function createMustacheTree(treeList, template, listData, ele, idStr, bindEvent) {
        debugger;
        var ren = Mustache.render(template, listData);
        $(ele).html(ren);
        recursiveRender(treeList, template, idStr, listData);
        nestableTree(bindEvent, ele);
    }
    function recursiveRender(deptTreeList, deptListTemplate, idStr, deptTreeListData) {
        if (deptTreeList && deptTreeList.length > 0) {
            $(deptTreeList).each(function (i, item) {
                deptTreeMap[item.id] = item;
                if (item.deptDtoList && item.deptDtoList.length > 0) {
                    deptTreeListData[getFirstAttr(deptTreeListData)] = item.deptDtoList;
                    var rendered = Mustache.render(deptListTemplate, deptTreeListData);
                    $(idStr + item.id).append(rendered);
                    recursiveRender(item.deptDtoList, deptListTemplate, idStr, deptTreeListData);
                }
            })
        }
    }








    // zTree
    <!-- 树结构相关 开始 -->
    var zTreeObj = [];
    var rolePrefix = 'u_';
    var nodeMap = {};

    var setting = {
        check: {
            enable: true,
            chkDisabledInherit: true,
            chkboxType: {"Y": "ps", "N": "ps"}, //auto check 父节点 子节点
            autoCheckTrigger: true
        },
        data: {
            simpleData: {
                enable: true,
                rootPId: 0
            }
        },
        callback: {
            onClick: onClickTreeNode
        }
    };

    function onClickTreeNode(e, treeId, treeNode) { // 绑定单击事件
        var zTree = $.fn.zTree.getZTreeObj("roleUserTree");
        zTree.expandNode(treeNode);
    }

    function createUserRoleTree(userId) {
        if (userId == -1) {
            return;
        }
        $.ajax({
            url: path+"/sys/role/roleUserTree.json",
            data : {
                userId: userId
            },
            type: 'POST',
            success: function (result) {
                if (result.ret) {
                    renderRoleTree(result.data);
                    $("#updateUserRoleId").val(userId);
                } else {

                }
            }
        });
    }
    function renderRoleTree(roleList) {
        zTreeObj = [];
        recursivePrepareTreeData(roleList);
        for(var key in nodeMap) {
            zTreeObj.push(nodeMap[key]);
        }
        $.fn.zTree.init($("#roleUserTree"), setting, zTreeObj);
    }

    function recursivePrepareTreeData(roleList) {
        // prepare nodeMap
        if (roleList && roleList.length > 0) {
            $(roleList).each(function(i, role) {
                var hasChecked = false;
                        zTreeObj.push({
                            id: rolePrefix + role.id,
                            pId: 0,
                            name: role.name,
                            chkDisabled: !role.hasAcl,
                            checked: role.checked,
                            dataId: role.id
                        });
            });
        }
    }
    function updateRoleUser() {
        var userId= $("#updateUserRoleId").val();
        var url = "/sys/role/changeUserRoles.json";
        var data = {
            userId:userId,
            roleIds: getZTreeSelectedId("roleUserTree","u_")
        };
        submitAjaxForm(url, data, function (result) {
            $(".closeModel").click();
        }, function (result) {

        });
    }
    function getZTreeSelectedId(ele,prefix) {
        var treeObj = $.fn.zTree.getZTreeObj(ele);
        var nodes = treeObj.getCheckedNodes(true);
        var v = "";
        for(var i = 0; i < nodes.length; i++) {
            if(nodes[i].id.startsWith(prefix)) {
                v += "," + nodes[i].dataId;
            }
        }
        return v.length > 0 ? v.substring(1): v;
    }
</script>

</body>

</html>
