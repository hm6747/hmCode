<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<jsp:include page="/static/jsp/common_quote.jsp"/>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">


    <title>添加商品</title>
</head>

<body class="gray-bg">
<div class="wrapper wrapper-content">
    <div class="row">
        <div class="col-sm-12">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5>添加商品</h5>
                </div>
                <div class="ibox-content">
                    <form method="get" class="form-horizontal" id="itemForm">
                        <div class="form-group">
                            <label class="col-sm-2 control-label">商品类目</label>
                            <div class="col-sm-10">
                                <button type="button" class="btn btn-primary" data-toggle="modal"
                                        data-target="#myModal">
                                    选择类目
                                </button>
                                <span style="margin-left: 20px;" id="cname"></span>
                                <input readonly name="cid" type="hidden" id="cid">
                            </div>
                            <div class="modal inmodal" id="myModal" tabindex="-1" role="dialog" aria-hidden="true">
                                <div class="modal-dialog">
                                    <div class="modal-content animated bounceInRight">
                                        <div class="modal-header">
                                            <div class="col-sm-12">
                                                <div class="ibox float-e-margins">
                                                    <div class="ibox-title">
                                                        <h5>选择商品类目</h5>
                                                        <div class="ibox-tools" id="nestable-menu" style="margin: 0;">
                                                            <button type="button" data-action="expand-all" class="btn btn-white btn-sm">展开所有</button>
                                                            <button type="button" data-action="collapse-all" class="btn btn-white btn-sm">收起所有</button>
                                                            <button type="button" class="btn btn-white" data-dismiss="modal">关闭</button>
                                                            <a class="collapse-link">
                                                                <i class="fa fa-chevron-up"></i>
                                                            </a>
                                                        </div>
                                                    </div>
                                                    <div class="ibox-content">
                                                        <div id="itemTree" class="dd nestable2"></div>
                                                        <script id="itemListTemplate" type="x-tmpl-mustache">
                                                        <ol class="dd-list">
                                                         {{#itemTreeList}}
                                                            <li class="dd-item" data-id="{{id}}" id="item_{{id}}">
                                                            <div class="dd-handle" style="text-align: left;">
                                                                <span class="label label-info"><i class="fa fa-users"></i></span> {{name}}
                                                            </div>
                                                            </li>
                                                         {{/itemTreeList}}
                                                         </ol>
                                                        </script>
                                                        <!--            <span style="float:right;">
                                                                      <a class="green item-edit" href="#" data-id="{{id}}" >
                                                                          <i class="ace-icon fa fa-pencil bigger-100"></i>
                                                                      </a>
                                                                      &nbsp;
                                                                      <a class="red item-delete" href="#" data-id="{{id}}" data-name="{{name}}">
                                                                          <i class="ace-icon fa fa-trash-o bigger-100"></i>
                                                                      </a>
                                                                  </span>-->
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                   <!--     <div class="modal-footer">
                                            <button type="button" class="btn btn-white" data-dismiss="modal">关闭</button>
                                       &lt;!&ndash;     <button type="button" class="btn btn-primary">保存</button>&ndash;&gt;
                                        </div>-->
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="hr-line-dashed"></div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">商品标题</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" aria-required="true" required name="title">
                            </div>
                        </div>
                        <div class="hr-line-dashed"></div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">商品卖点</label>
                            <div class="col-sm-10">
                                <textarea id="ccomment" name="sellPoint" class="form-control help-block m-b-none"
                                          required="" aria-required="true" aria-invalid="false"
                                          aria-describedby="ccomment-error"></textarea>
                            </div>
                        </div>
                        <div class="hr-line-dashed"></div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">商品价格</label>

                            <div class="col-sm-10">
                                <input type="text" class="form-control" aria-required="true" name="price">
                            </div>
                        </div>
                        <div class="hr-line-dashed"></div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">商品数量</label>

                            <div class="col-sm-10">
                                <input type="text" class="form-control" name="num">
                            </div>
                        </div>
                        <div class="hr-line-dashed"></div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">条形码</label>

                            <div class="col-sm-10">
                                <input type="text" class="form-control" name="barcode">
                            </div>
                        </div>
                        <div class="hr-line-dashed"></div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">商品图片</label>

                            <div class="col-sm-10">
                                <input type="file" name="image" id="imgUpload" multiple/>
                                <p class="help-block">支持jpg、jpeg、png、gif格式，大小不超过2.0M</p>
                            </div>
                        </div>
                        <div class="hr-line-dashed"></div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">商品描述</label>
                            <div class="col-sm-10">
                                <div class="ibox float-e-margins">
                                    <div class="ibox-title">
                                        <button id="edit" class="btn btn-primary btn-xs m-l-sm"
                                                type="button">编辑
                                        </button>
                                        <button id="save" class="btn btn-primary  btn-xs"
                                                type="button">保存
                                        </button>
                                    </div>
                                    <div class="ibox-content" id="eg">

                                        <div class="click2edit wrapper">
                                        </div>

                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="hr-line-dashed"></div>
                        <div class="form-group">
                            <div class="col-sm-4 col-sm-offset-2">
                                <button class="btn btn-primary" type="submit">保存内容</button>
                                <button class="btn btn-white" type="button" onclick="window.history.go(-1)">取消</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    var itemTreeList; //缓存树形目录
    var itemTreeMap = {};
    $(document).ready(function () {
        commonValidate("itemForm", submitForm);
        initSummernote();
        initFileInput("imgUpload", "");
        itemTreeCreate();
    });

    //提交表单
    function submitForm() {
        $.post(path+"/item/save", $("#itemForm").serialize(), function (data) {
            if (data.status == 200) {
                window.location.href="item.html";
            }
        });
    }
    //富文本初始化
    function initSummernote() {
   /*     $('.i-checks').iCheck({
            checkboxClass: 'icheckbox_square-green',
            radioClass: 'iradio_square-green',
        });*/
        $('.summernote').summernote({
            lang: 'zh-CN',
            callbacks: {
            onKeyup: function(e) {
                console.log('Key is released:', e.keyCode);
            }
            }
        });
        $("#save").on("click", function () {
            console.log($(".note-editable").html())
            save();
        });
        $("#edit").on("click", function () {
            edit();
        });
        $("#edit").click();
    }

    //初始化fileinput控件（第一次初始化）
    function initFileInput(ctrlName, uploadUrl) {
        var control = $('#' + ctrlName);
        control.fileinput({
            language: 'zh', //设置语言
            uploadUrl: uploadUrl, //上传的地址
            allowedFileExtensions: ['jpg', 'png', 'gif'],//接收的文件后缀
            maxFileCount: 5,
            showUpload: false, //是否显示上传按钮
            showCaption: false,//是否显示标题
            browseClass: "btn btn-primary", //按钮样式
            enctype: 'multipart/form-data',
            validateInitialCount: true,
            previewFileIcon: "<i class='glyphicon glyphicon-king'></i>",
            msgFilesTooMany: "选择上传的文件数量({n}) 超过允许的最大数值{m}！",
            removeFromPreviewOnError: true, //当文件不符合规则，就不显示预览
            //showPreview: true,//展前预览
            showRemove: true, //是否显示删除按钮
        }).on('filepreupload', function (event, data, previewId, index) {     //上传中
            var form = data.form, files = data.files, extra = data.extra,
                    response = data.response, reader = data.reader;
            console.log('文件正在上传');
        }).on("fileuploaded", function (event, data, previewId, index) {    //一个文件上传成功
            console.log('文件上传成功！' + data.id);

        }).on('fileerror', function (event, data, msg) {  //一个文件上传失败
            console.log('文件上传失败！' + data.id);
        })
    }
    function edit() {
        $("#eg").addClass("no-padding");
        $('.click2edit').summernote({
            lang: 'zh-CN',
            focus: true
        });
    }
    function save() {
        $("#eg").removeClass("no-padding");
        var aHTML = $('.click2edit').code(); //save HTML If you need(aHTML: array).
        $('.click2edit').destroy();
    }
    function itemTreeCreate() {
        var itemListTemplate = $("#itemListTemplate").html();
        Mustache.parse(itemListTemplate);
        loadItemTree(itemListTemplate);
    }
    function loadItemTree(itemListTemplate) {
        $.ajax({
            url: path+"/item/cat/tree.json",
            success: function (result) {
                itemTreeList = result;
                var rendred = Mustache.render(itemListTemplate, {itemTreeList: itemTreeList});
                $("#itemTree").html(rendred);
                recursiveRenderItem(itemTreeList, itemListTemplate);
                nestableTree(nesBindEvent,$("#itemTree"));
            }
        })
    }
    var nesBindEvent = function () {
        $("#itemTree .dd-handle").click(function () {
            $("#cname").html($(this).text().trim());
            $("#cid").val($(this).parent().attr("data-id"));
            $(".btn-white").click();
        })
    }
    function recursiveRenderItem(itemTreeList, itemListTemplate) {
        if (itemTreeList && itemTreeList.length > 0) {
            $(itemTreeList).each(function (i, item) {
                debugger;
                itemTreeMap[item.id] = item;
                if (item.itemCatDtoList && item.itemCatDtoList.length > 0) {
                    var rendered = Mustache.render(itemListTemplate, {itemTreeList: item.itemCatDtoList});
                    $("#item_" + item.id).append(rendered);
                    recursiveRenderItem(item.itemCatDtoList, itemListTemplate)
                }
            })
        }
    }
</script>

</body>

</html>
