// 全局变量
var dlg = null;
var settingTableDialog = null;
//debugger
var meetType = getUrlParams("meetType");
var batchId = "";
var funID = getUrlParams("funID");
var A00 = getUrlParams("A00");
var defualtGroup = "00000000-0000-0000-0000-000000000000";
var groupIdArr = []; //所有的组Id

// 声明所有从后台调用的 api
var api = Api(function(ajax, reg) {
        return {
            getPersonGroup: function(params) {
                return ajax({
                    type: 'post',
                    data: params,
                    dataType: "json",
                    url: '/gbrm/common/controller/Rywh/getPersonGroup'
                }).then(function(value) {
                    var groupData = value.result;
                    var arr = [];
                    for (var i = 0; i < groupData.length; i++) {
                        var obj = {};
                        if (groupData[i].PID == '') {
                            obj["id"] = groupData[i].GROUPID;
                            obj["text"] = groupData[i].GROUPNAME;
                            obj["num"] = groupData[i].PERSONCOUNT_JS;
                            obj["index"] = groupData[i].BJQE1003;
                            obj["isChild"] = false;
                            arr.push(obj);
                            for (var j = 0; j < groupData.length; j++) {
                                var newObj = {};
                                if (containFun1(arr, groupData[j].PID) && containFun2(arr, groupData[j].GROUPID)) {
                                    newObj["id"] = groupData[j].GROUPID;
                                    newObj["text"] = groupData[j].GROUPNAME;
                                    newObj["num"] = groupData[j].PERSONCOUNT_JS;
                                    newObj["index"] = groupData[j].BJQE1003;
                                    newObj["isChild"] = true;
                                    arr.push(newObj);
                                }
                            }
                        }
                    }
                    return arr;
                })
            },
            //删除人员
            deletePersonRecord:function(dataPersons){
            	return ajax({
            		type: 'POST',
            		data: {
            			'DATA':dataPersons// 必传
            		},
            		url: '/gbrm/common/controller/Rywh/deletePersonRecord'
            	})
            },
            //查询组下面的人
            getPersonList: function(params) {
                return ajax({
                    type: 'post',
                    data: params,
                    dataType: "json",
                    url: '/gbrm/common/controller/Rywh/getPersonListHKGY'
                })
            },
            //对组的操作
            groupTreeOption: function(params) {
                return ajax({
                    type: 'post',
                    data: params,
                    dataType: "json",
                    url: '/gbrm/common/controller/Rywh/groupTreeOption'
                })
            },
            //取消登记
            deleteRecord: function(parameters) {
                return ajax({
                    type: "post",
                    cache: false,
                    data: parameters,
                    dataType: "json",
                    url: "/gbrm/rydj/controller/Rydj/deleteRecord"
                })
            },
            //保存排序
            saveOrder: function(parameters) {
                return ajax({
                    type: "post",
                    cache: false,
                    data: parameters,
                    dataType: "json",
                    url: "/gbrm/rydj/controller/Rydj/saveOrder"
                })
            },
            //创建会议
            addMeet: function(MeetTime, MEndTime, content) {
                return ajax({
                    type: 'post',
                    data: {
                        meetType: meetType,
                        MeetTime: MeetTime,
                        MEndTime: MEndTime,
                        content: content
                    },
                    url: '/gbrm/common/controller/Jshy/addMeet'
                })
            },
            getReport: function(reportId) {
                return ajax({
                    type: 'post',
                    data: {
                        funId: reportId
                    },
                    url: '/gbrm/common/controller/Custom/getReport'
                })
            },
            getDefaultBatchId: function() {
                return ajax({
                    type: 'post',
                    data: {
                        meetType: meetType
                    },
                    dataType: "json",
                    url: '/gbrm/common/controller/Rywh/getDefaultBatchId'
                })
            },
            getMeetInfo: function() {
                return ajax({
                    type: 'get',
                    data: {
                        batchId: batchId
                    },
                    dataType: "json",
                    url: '/gbrm/common/controller/Jshy/getMeetInfo'
                })
            }
        }
    })
    //任免组之间的判断
var containFun1 = function(arr, pId) {
        var flag = false;
        for (var i = 0; i < arr.length; i++) {
            if (pId == arr[i].id) {
                flag = true;
                return flag;
            }
        }
        return flag;
    }
    //任免组之间的判断
var containFun2 = function(arr, id) {
        var flag = false;
        for (var i = 0; i < arr.length; i++) {
            if (id == arr[i].id) {
                flag = false;
                return flag;
            } else {
                flag = true;
            }
        }
        return flag;
    }
    //任免小组弹框事件
function showSettingTable(id, selectId, selectNum) {
    if (selectId == "00000000-0000-0000-0000-000000000000") {
    	zDialog.alert("不能对待分配进行编辑操作！");
        return;
    }
    if (dialog) dialog.close()
    var dialog = new zDialog();
    dialog.Model = true;
    dialog.Width = 836;
    dialog.Height = 280;
    if (id === 'add') {
        dialog.Title = "新建分组";
        dialog.URL = "/gbrm/common/rywh/setting.html?type=add&meetType=" + meetType +"&treeType=1";
    }
    if (id === 'edit') {
        // 判断是否有分组被选中
        if (!selectId) {
        	zDialog.alert("请选择分组");
            return;
        }
        dialog.Title = "编辑分组";
        dialog.URL = "/gbrm/common/rywh/setting.html?type=edit&selectId=" + selectId + "&selectNum=" + selectNum + "&meetType=" + meetType+"&treeType=1";
    }
    dialog.show();

    settingTableDialog = dialog

}
//删除组信息
var deletetGroupInfo = function(selectId) {
        var frontData = {
            "fenzuid": selectId
        }
        var params = {
            "treetype": 1,
            "optype": "delete",
            "meetType": meetType,
            "data": JSON.stringify(frontData)
        }
        api.groupTreeOption(params).then(function(value) {
            if (value.code == "-1") {
            	zDialog.alert(value.message)
            } else {
            	zDialog.alert('删除成功！',function(){            		
            		Msger(window.top).emit('reflash',[true]);
            	})
            }
        }).catch(function(err) {
        	zDialog.alert(err);
        })
    }
    //左边树任免小组向上移动
var groupMoveUp = function() {
        if (selectId == "00000000-0000-0000-0000-000000000000") {
        	zDialog.alert("不能对待分配进行上移操作！");
            return;
        }
        //获取选中的对象
        var frontData = {
            "fenzuid": selectId,
            "orderType": "up"
        }
        var params = {
            "treetype": 1,
            "optype": "order",
            "meetType": meetType,
            "data": JSON.stringify(frontData)
        }
        api.groupTreeOption(params).then(function(value) {
            if (value.result == 0) {
            	zDialog.alert(value.message);
            } else {
                Msger(window.top).emit('reloadList', [selectId]);
            }
        })
    }
    //左边树任免小组向下移动
var groupMoveDown = function() {
        if (selectId == "00000000-0000-0000-0000-000000000000") {
        	zDialog.alert("不能对待分配进行下移操作！");
            return;
        }
        //获取选中的对象
        var frontData = {
            "fenzuid": selectId,
            "orderType": "down"
        }
        var params = {
            "treetype": 1,
            "optype": "order",
            "meetType": meetType,
            "data": JSON.stringify(frontData)
        }
        api.groupTreeOption(params).then(function(value) {
            if (value.result == 0) {
            	zDialog.alert(value.message);
            } else {
                Msger(window.top).emit('reloadList', [selectId]);
            }
        })
    }
    //显示所有任免小组的人员
function showAllPerson() {
    var grid = mini.get("#datagrid_cbjymd");
    var personGroupId = groupIdArr.join(',').replace(/\"/g, "");
    Msger(window.top).emit('loadPersonData', [personGroupId]);
}
//任免组
var sider = {
    target: null,
    get: function() {
        if (!this.target) this.init()
        return this.target
    },
    init: function() {
        this.target = Shelf.get('rmfzSider')($('.sider').get(0), {
            btns: {
                up: function() { //上移
                    groupMoveUp(selectId);
                },
                down: function() { //下移
                    groupMoveDown(selectId);
                },
                show: function() { //全显按钮的事件绑定
                    showAllPerson();
                },
                set: {
                    list: [{
                        id: 'add',
                        text: '新建'
                    }, {
                        id: 'edit',
                        text: '编辑'
                    }, {
                        id: 'delete',
                        text: '删除'
                    }],
                    click: function(a) {
                        if (a.id === 'add') { // 新建或者编辑
                            showSettingTable(a.id);
                        } else if (a.id === 'edit') {
                            showSettingTable(a.id, selectId, selectNum);
                        } else if (a.id === 'delete') { // 删除
                            if (!selectId) {
                            	zDialog.alert("请选择分组");
                                return;
                            }
                            if (selectId == "00000000-0000-0000-0000-000000000000") {
                            	zDialog.alert("不能对待分配进行删除操作！");
                                return;
                            }
                            zDialog.confirm("确定删除吗?",function(){
                            	deletetGroupInfo(selectId);
                            });
                        }
                    }
                }
            },
            itemClick: function(a) {
                selectId = a.id;
                selectNum = a.num;
                Msger(window.top).emit('loadPersonData', [selectId]);
            }
        })
    }
}
var siderDw = {
        target: null,
        get: function() {
            if (!this.target) this.init()
            return this.target
        },
        init: function() {
            this.target = Shelf.get('rmfzSider')($('.sider.dw').get(0), {
                btns: {
                    up: function() { //上移
                        groupMoveUp(selectId);
                    },
                    down: function() { //下移
                        groupMoveDown(selectId);
                    },
                    show: function() { //全显按钮的事件绑定
                        showAllPerson();
                    },
                    set: {
                        list: [{
                            id: 'add',
                            text: '新建'
                        }, {
                            id: 'edit',
                            text: '编辑'
                        }, {
                            id: 'delete',
                            text: '删除'
                        }],
                        click: function(a) {
                            if (a.id === 'add') { // 新建或者编辑
                                showSettingTable(a.id);
                            } else if (a.id === 'edit') {
                                showSettingTable(a.id, selectId, selectNum);
                            } else if (a.id === 'delete') { // 删除
                                if (!selectId) {
                                	zDialog.alert("请选择分组");
                                    return;
                                }
                                zDialog.confirm("确定删除吗?",function(){                                	
                                	deletetGroupInfo(selectId);
                                });
                            }
                        }
                    }
                },
                itemClick: function(a) {
                    selectId = a.id;
                    selectNum = a.num;
                    Msger(window.top).emit('loadPersonData', [selectId]);
                }
            })
        }
    }
    //表头格式调整
function getColumns(columns) {
    var result = (columns || []).map(function(v) {
    		debugger
            var header = v.colname
            return $.extend({
                    name: header,
                    // 列头属性值，用来对应填充表单
                    field: header,
                    // 是否可见，默认为全部可见，针对个别字段做特殊处理
                    visible: true,
                    // 对齐方式，个别列下的内容需要左对齐
                    align: GetColumnAlign(header),
                    // 设置不允许grid排序
                    allowSort: false,
                    // 每列的宽度设置
                    width: GetColumnWidth(header),
                    // 列头居中
                    headerAlign: "center",
                    // 设置列头折行
                    header: InsertBrAndSpace(header),
                    // 列头样式，字体加粗，背景色
                    // headerStyle: "font-weight:bold;background: #f2f2f2!important",
                    // 单元格样式
                    // cellStyle: "line-height:20px;",
                    // 列不能拖动
                    allowMove: false,
                    // 不能resieze
                    allowResize: false,
                    allowCellEdit : true,
                    allowCellSelect : true,
                    showModified : false,
                    // 自动转移html,用于折行
                    autoEscape: true
                }, // 设置隐藏
                header === 'SID' ? {
                    visible: false
                }:header === 'PERSONID' ? {
                    visible: false
                } : header === '排序' ? {
                    visible: false
                } : header === 'BATCHID' ? {
                    visible: false
                } : header === 'RECORDID' ? {
                    visible: false
                } : header === 'PERSONFLOWID' ? {
                    visible: false
                } : header === 'GROUPID' ? {
                    visible: false
                } : header === 'RZTYPE' ? {
                    visible: false
                } : header === '姓名' ? {
                    // 设置链接
                    renderer: function(v) {
                        var newStr = "";
                        switch (meetType) {
                            case "CBGT":
                                newStr = 'gbrm.cbgt.ryxxwh';
                                break;
                            case "XSJHB":
                                newStr = 'gbrm.xsjhb.ryxxwh';
                                break;
                            case "CWH":
                                newStr = 'gbrm.cwh.ryxxwh';
                                break;
                            default:
                                break;
                        }
                        //                    return '<a  href="/gbrm/common/rywh/ryxxwh.html?personId=A00&batchId=bId&groupId=gId&rzType=rzt&flid=03&meetType=mt&funID=ct">header</a>'.replace('A00', v.row.PERSONID).replace('bId', v.row.BATCHID).replace('gId', v.row.GROUPID).replace('rzt', v.row.RZTYPE).replace('header', v.row["姓名"]).replace('mt', meetType).replace('ct', newStr);
                        return '<a href="javascript:void(0)" onclick="onCbjymdNameClick(uid)">header</a>'.replace('uid', v.record._uid).replace('header', v.row["姓名"]);
                    }
                } : header === "出生日期" ? { // 设置年龄
                    renderer: function(v) {
                        return (v.row["出生日期"]).replace("\n", "<br>");
                    }
                } : header === "序号" ? { // 序号
                	editor : {
                		type : 'textbox'
                	},
                	 // 设置链接
                    renderer: function(v) {
                      return v.row['序号'];
                  }
                } : header === "文化程度" ? { // 设置年龄
                    renderer: function(v) {
                        return (v.row["文化程度"]);
                    }
                } : header === "拟任职务" ? {
                    renderer: function (v) {
                        return (v.row["拟任职务"]).replace("&lt;br/&gt;", "<br>");
                    }
                } : header === "拟免职务" ? {
                    renderer: function (v) {
                        return (v.row["拟免职务"]).replace("&lt;br/&gt;", "<br>");
                    }
                } : header === "RMVZMS" ? {
                    renderer: function (v) {
                        return (v.row["RMVZMS"]);
                    }
                } 
                : {}
            )
        })
        // 添加多选框和序号栏
    result.splice(0, 0, {
        type: "checkcolumn",
        align: "center",
        width: "2",
        //headerStyle: "background: #f2f2f2!important"
    });

    return result;
}

function onCbjymdNameClick(uid) {
    var grid = mini.get("#datagrid_cbjymd");
    var row = grid.getRowByUID(uid);
    if (row.PERSONID == "00000000-0000-0000-0000-000000000000") {
        //		var url = '/gbrm/fxypdy/djdetail.html?A00='+row.PERSONID+'&meetType=CBGT'+'&SID='+row.BATCHID
        //		openDialog('职务登记',url)
        //		adjustDuty();
        adjustDutyNew();
    } else {
        var url = '/gbrm/common/rywh/ryxxwh.html?personId=' + row.PERSONID + '&batchId=' + row.BATCHID + '&groupId=' + row.GROUPID + '&rzType=rzt&flid=03&meetType=' + meetType
        window.open(url, "_blank")
    }
}
//设置表格内容的对齐方式
function GetColumnAlign(header) {
    // 此三列内容过长应该左对齐
    if (header == "现任职务" || header == "拟任职务" || header == "拟免职务" || header == "学校专业") {
        return "left";
    } else {
        return "center";
    }
}
// 单元格宽度
function GetColumnWidth(obj) {
    switch (obj) {
        case "checkcolumn":
            return "3";
        case "indexcolumn":
            return "5";
        case "序号":
            return "5";
        case "姓名":
            return "5";
        case "性别":
            return "5";
        case "出生日期":
            return "7";
        case "文化程度":
            return "9";
        case "学校专业":
            return "12";
        case "现任职务":
            return "15";
        case "拟任职务":
            return "15";
        case "拟免职务":
            return "15";
        case "RMVZMS":
            return "10";
        case "备注":
            return "6";

        default:
            break;
    }
}
// 给列头添加折行和空格
function InsertBrAndSpace(colName) {
    switch (colName) {
        case "序号":
            return "序号";
        case "性别":
            return "性别";
        case "出生日期":
            return "出生日期<br/>（岁）";
        case "RMVZMS":
            return "任/免职类别";
        default:
            return colName;
    }
}
Msger()
    .regist('init', function() {
    	mini.parse();
        api.getDefaultBatchId().then(function(bid) {
        	debugger
            batchId = bid;
            Msger(window.top).emit('reload');
            Msger(window.top).emit('reloadDw');

            mini.parse($('#datagrid_cbjymd').get(0))
            Msger(window.top).emit('reloadList');
            // 加载数据列表
            Msger(window.top).emit('reloadListDw');
            
         // 初始化分组排序下拉列表
            popList = Shelf.get('menuBtn')(document.getElementById('order_btn'), {
                    text: '排序',
                    list: [
                        { id: "up", text: '上移' },
                        { id: "down", text: '下移' }
                    ],
                    width: 100,
                    itemClick: function(item) {
                        var templateName = "";
                        switch (item.id) {
                            case "up":
                                up_btn();
                                break;
                            case "down":
                                down_btn();
                                break;
                            default:
                                break;
                        }
                    }
                })
                //调整分组
            $('#adjust_btn').get(0).onclick = function() {
                    adjust_btn();
                }
            //删除初始人员名单按钮
            $('#delete_btn').get(0).onclick = function() {
            	 var grid = mini.get("datagrid_cbjymd");
            	 var rows = grid.getSelecteds();
            	 if (rows.length < 1) {
            	    	zDialog.alert("请在列表中选择人员！");
            	    	return;
            	 }
            	 zDialog.confirm("确定要删除选中的人员吗",function(){
            		 var PersonData = [];
            		 var data=mini.get('datagrid_cbjymd').getSelecteds();
      	    	      data.map(function(v,i){
      		    	      PersonData.push({
      		    		      SID : v.SID
      		    	   })
      	    	    });
                     api.deletePersonRecord(JSON.stringify(PersonData)).then(function() {
                     	zDialog.alert("删除成功！",function(){            		
                     		var gridId = 'datagrid_cbjymd';
                       		var grid=mini.get(gridId);
                       		var rows = grid.getSelecteds();
                       		for(var i = 0 ; i< rows.length;i++){
                       			var row = rows[i];
                       			grid.removeRow(row, false)
                       		}
                       		saveOrder();
                            setTimeout(function(){
                            	showAllPerson();
                           	},1000)
                     	});
                     })
                 })
            }
            //删除动议酝酿的按钮
            $('#delete_btnyn').get(0).onclick = function() {
           	 var grid = mini.get("end_meet-grid");
           	 var rows = grid.getSelecteds();
           	 if (rows.length < 1) {
           	    	zDialog.alert("请在列表中选择人员！");
           	    	return;
           	 }
           	 zDialog.confirm("确定要删除选中的人员吗",function(){
           		 var PersonData = [];
           		 var data=mini.get('end_meet-grid').getSelecteds();
     	    	      data.map(function(v,i){
     		    	      PersonData.push({
     		    		      SID : v.SID
     		    	   })
     	    	    });
                    api.deletePersonRecord(JSON.stringify(PersonData)).then(function() {
                    	zDialog.alert("删除成功！",function(){            		
                    		EndMeetGrid.init('end_meet-grid')
                    	});
                    })
                })
           }
                //取消登记
            $('#cancel_btn').get(0).onclick = function() {
                    cancel_btn();
                }
                //tab页签
            Shelf.get('simpleTabs')(
            		//debugger
                $('.globe').get(0), {
                    pages: [{
                        title: '初步建议名单',
                        id: 'cbjymd',
                        target: $('#panel1').get(0),
                    }, {
                    	//debugger
                        title: '动议酝酿',
                        id: 'dyjlwh',
                        target: $('#panel2').get(0),
                    }]
                })
        });

        
        api.getReport('REPORT_10').then(function(v) {
            // 输出材料绑定
            Shelf.get('menuBtn')(
                document.getElementById('output_btn'), {
                    text: '输出',
                    list: mini.decode(v).list,
                    width: 200,
                    //点击事件绑定
                    itemClick: function(item) {
                        var format = ""; // 输出材料的格式；"word" || "excel"
                        var templateName = "";
                        var allID = ""; // 所有选中人员的id
                        var saveAsFileName = "";
                        var allData = [];
                        var grid = mini.get("datagrid_cbjymd");
                        // 模板所在目录，在任免系统中默认为gbrm,如果是“任免表”输出，沿用干部信息管理的目录，修改为gbxxgl
                        var templateDir = "gbrm";

                        switch (item.id) {
                            case "dy_rmb":
                                var templateDir = "gbxxgl";
                                templateName = "gbrmspb"; // 推荐考察工作方案(单位材料)
                                saveAsFileName = "任免表";
                                format = "word";
                                break;
                            case "dy_dyypj":
                                templateName = "dy_dyypj"; // 模板-会议推荐签到表(单位材料)
                                saveAsFileName = "动议批阅件";
                                format = "word";
                                break;
                            case "dy_mcmb":
                                templateName = "dy_mcmb"; // 考察组讲话稿(单位材料)
                                saveAsFileName = "名册模板";
                                format = "excel";
                                break;
                            default:
                                break;
                        }

                        // 设置参数
                        var params = {
                            templateDir: templateDir,
                            templateName: templateName, //模板名称，不带扩展名xls
                            isExportMultiFile: "0", // 是否多文件输出（1或true）
                            isDownload: "1", //下载
                            isDownLoad: "1", //下载
                            A00: '839E2132-0018-43B0-A202-7AC795F21ED8',
                            saveAsFileName: saveAsFileName,
                            v: 1
                        }
                        if (templateName == 'gbrmspb') {
                            //干部任免表，需要增加一个按照页面的排序
                            var records = grid.getSelecteds();
                            var selectFlag = true; //
                            if (!records || records.length == 0) {
                                //没有选择人，则获取全部非空岗人员
                                records = grid.getData();
                                selectFlag = false;
                            }
                            for (var i = 0; i < records.length; i++) {
                                if (selectFlag && records[i].PERSONID == '00000000-0000-0000-0000-000000000000') {
                                	zDialog.alert('存在空岗数据,请确认选择数据');
                                    return;
                                } else if (records[i].PERSONID == '00000000-0000-0000-0000-000000000000') {
                                    continue;
                                }
                                //获取批次ID和人员组ID
                                if (i == 0) {
                                    personGroupID = records[0].GROUPID;
                                    BatchID = records[0].BATCHID;
                                }
                                allData.push(records[i].PERSONID);
                            }
                            if (allData.length < 1) {
                            	zDialog.alert('无可输出人员数据！');
                                return;
                            }
                            //判断是否有需要输出的人员

                            allID = allData.join(",");
                            params.A00 = allID //弃用
                            params.ids = allID //所有主键都用ids传
                            params.personGroupID = personGroupID
                            params.BatchID = BatchID

                            window.ReportWordHelper.exportWord(params);
                        } else {
                            if (format === "excel") {
                                window.ReportExcelHelper.exportExcel(params);
                            } else if (format === "word") {
                                window.ReportWordHelper.exportWord(params);
                            }
                        }

                    }
                })
        })
        
        
        
        api.getReport('REPORT_71').then(function(v) {
            // 输出材料绑定
            Shelf.get('menuBtn')(
                document.getElementById('output_btngrid'), {
                    text: '输出',
                    list: mini.decode(v).list,
                    width: 200,
                    //点击事件绑定
                    itemClick: function(item) {
                        var format = ""; // 输出材料的格式；"word" || "excel"
                        var templateName = "";
                        var allID = ""; // 所有选中人员的id
                        var saveAsFileName = "";
                        var allData = [];
                        var grid = mini.get("end_meet-grid");
                        // 模板所在目录，在任免系统中默认为gbrm,如果是“任免表”输出，沿用干部信息管理的目录，修改为gbxxgl
                        var templateDir = "gbrm";
                        switch (item.id) {
                            case "dy_rmb":
                                var templateDir = "gbxxgl";
                                templateName = "gbrmspb"; // 推荐考察工作方案(单位材料)
                                saveAsFileName = "任免表";
                                format = "word";
                                break;
                            default:
                                break;
                        }

                        // 设置参数
                        var params = {
                            templateDir: templateDir,
                            templateName: templateName, //模板名称，不带扩展名xls
                            isExportMultiFile: "0", // 是否多文件输出（1或true）
                            isDownload: "1", //下载
                            isDownLoad: "1", //下载
                            A00: '839E2132-0018-43B0-A202-7AC795F21ED8',
                            saveAsFileName: saveAsFileName,
                            v: 1
                        }
                        if (templateName == 'gbrmspb') {
                            //干部任免表，需要增加一个按照页面的排序
                            var records = grid.getSelecteds();
                            var selectFlag = true; //
                            if (!records || records.length == 0) {
                                //没有选择人，则获取全部非空岗人员
                                records = grid.getData();
                                selectFlag = false;
                            }
                            for (var i = 0; i < records.length; i++) {
                                if (selectFlag && records[i].PERSONID == '00000000-0000-0000-0000-000000000000') {
                                	zDialog.alert('存在空岗数据,请确认选择数据');
                                    return;
                                } else if (records[i].PERSONID == '00000000-0000-0000-0000-000000000000') {
                                    continue;
                                }
                                //获取批次ID和人员组ID
                                if (i == 0) {
                                    personGroupID = records[0].GROUPID;
                                    BatchID = records[0].BATCHID;
                                }
                                allData.push(records[i].A00);
                            }
                            if (allData.length < 1) {
                            	zDialog.alert('无可输出人员数据！');
                                return;
                            }
                            //判断是否有需要输出的人员

                            allID = allData.join(",");
                            params.A00 = allID //弃用
                            params.ids = allID //所有主键都用ids传
                            params.personGroupID = personGroupID
                            params.BatchID = BatchID

                            window.ReportWordHelper.exportWord(params);
                        } else {
                            if (format === "excel") {
                                window.ReportExcelHelper.exportExcel(params);
                            } else if (format === "word") {
                                window.ReportWordHelper.exportWord(params);
                            }
                        }

                    }
                })
        })
        
    	var gridId = 'datagrid_cbjymd';
		var grid=mini.get(gridId);
		grid.on("cellclick", function (e) {
			// 当前选中行对象
			var currentRow = grid.getSelected();
//			 var row = {};
//	            grid.addRow(row);
			//grid.beginEditCell(currentRow,"XH");
//			grid.beginEditRow(currentRow);
			//alert()
		})
		/**
		 * 如果值没有该表，则不会触发
		 * 
		 * */
		// 编辑结束时发生
		grid.on("cellendedit", function (e) {
			// 当前选中行对象
			var currentRow = grid.getSelected();
			// 当前选中行的下标
			var currentRowIndex = e.rowIndex
			// 输入的下表值
			var index = parseInt(e.value);
			
			if(e.field == '序号'){
				// 判断上移还是下移
				if(index == currentRowIndex+1){
					
				}else if(index < currentRowIndex+1){//上移
					grid.moveRow(currentRow,parseInt(index) -1)
					//修改区间内行的序号
					for(var i = index-1;i <= currentRowIndex ;i++ ){
						var tempRow = grid.getRow(i);
						grid.updateRow(tempRow, { 序号: i+1 })
					}
				}else{//下移
					grid.moveRow(currentRow,parseInt(index))
					//修改区间内行的序号
					for(var i = currentRowIndex;i < index ;i++ ){
						var tempRow = grid.getRow(i);
						grid.updateRow(tempRow, { 序号: i+1 })
					}
				}
				//保存排序，从1开始
				saveOrder(1);
			}
			});
		// 编辑值提交前发生
		grid.on("cellcommitedit", function (e) {
			// 当前选中行的下标
			var currentRowIndex = e.rowIndex
			var index = e.value;
			if(e.field == '序号'){
					  // 判断输入的是不是纯数字
					  var re = /^\d{1,}$/;
					　if (!re.test(index)) {
					　　　　zDialog.alert("请输入数字");
							// 把文本框的内容修改回来
							e.value=currentRowIndex+1
							
							return;
					　　} 
					  if(index == 0){
						 e.value=1
					  }
					index = parseInt(index)
					// 判断输入的行号是否存在
					var gridLength = grid.data.length;
					if(index <= gridLength){
						
					}else{
						// 把文本框的内容修改回来
						e.value=currentRowIndex+1
					}
						
				}
			});
        
        
    })

// 全局事件管理
Msger(window.top)
    .regist('reloadList', function(selectedGroupId,isGroupDel) {
        var params = {
            "type": "1",
            "flid": "03",
            "meetType": meetType,
            "showcount": '0'
        }
        api.getPersonGroup(params).then(function(data) {
            sider.get().emit('loadList', [data])
            if (data.length > 0) {
                for (var i = 0; i < data.length; i++) {
                    groupIdArr[i] = data[i].id;
                }
                if (selectedGroupId == null || typeof(selectedGroupId) == "undefined" || isGroupDel) {
                    selectId = data[0].id;
                    selectNum = data[0].num;
                } else {
                    selectId = selectedGroupId
                }

                sider.get().emit('select', [selectId])
                Msger(window.top).emit('loadPersonData', [selectId]);
            } else {
                //组id
                Msger(window.top).emit('loadPersonData', ['99999999-9999-9999-999999999999']);
            }
        })
    })
    .regist('loadPersonData', function(groupId) {
    	
        var grid = mini.get("#datagrid_cbjymd");
        grid.mask();
        var params = {
            "personGroupId": groupId,
            "personGroupType": '1',
            "flid": "03",
            "meetType": meetType
        };
        grid.loading();
        // 发送请求
        api.getPersonList(params).then(function(value) {
        	//debugger
            // 编辑列头
            var columns = getColumns(value.columns);
            // 设置列头
            grid.setColumns(columns);
            // 设置数据
            grid.set({
                data: value.dt.rows
            })
            grid.unmask();
        }).catch(function(err) {
            grid.unmask();
            zDialog.alert(err.responseText);
        })
    })
    .regist('reload', function() {
        sider.get().emit('getSelected');
    })
    .regist('reloadDw', function(selectedGroupId) {
        //		var params = {
        //	            "type": "1",
        //	            "flid": "03",
        //	            "meetType": meetType,
        //	            "showcount": '0'
        //	     }
        //		api.getPersonGroup(params).then(function (data) {
        //			siderDw.get().emit('loadList',[data])
        //	      	if(data.length > 0){
        //	      		for(var i =0;i<data.length;i++){
        //	      			groupIdArr[i] = data[i].id;
        //	      		}
        //	      		if(selectedGroupId == null || typeof(selectedGroupId)== "undefined"){
        //	      			selectId = data[0].id;
        //	      			selectNum = data[0].num;
        //	      		}else{
        //	      			selectId = selectedGroupId
        //	      		}
        //	      		
        //	      		siderDw.get().emit('select',[selectId])
        //	      		Msger(window.top).emit('loadPersonData',[selectId]);
        //	      	}else{
        //	      		//组id
        //	      		Msger(window.top).emit('loadPersonData',['99999999-9999-9999-999999999999']);
        //	      	}
        //	    })
    })
    .regist('reloadListDw', function() {
        //初始化结束会议表格
        mini.parse();
        var date = new Date();
        mini.get("date1").setValue(date);
        api.getMeetInfo().then(function(meetInfo){
        	if(meetInfo){
        		if(meetInfo.meettime){
        	        mini.get("date1").setValue(meetInfo.meettime);
        		}
        		/*if(meetInfo.mendtime){
        	        mini.get("date2").setValue(meetInfo.mendtime);
        		}
        		if(meetInfo.content){
        			$("#content").val(meetInfo.content);
        		}*/
        	}        	
        });


        EndMeetGrid.init('end_meet-grid')

        siderDw.get().emit('loadList', [null])
    })
    .regist('closeDlg', function() {
        if (dlg) dlg.close();
        dlg = null;
        Msger(window.top).emit('reload');
    })
    .regist('closeDlgDw', function() {
        if (dlg) dlg.close();
        dlg = null;
        Msger(window.top).emit('reloadDw');
    })
    .regist('closeXzkcdwDlg', function() {
        if (dlg) dlg.close();
        dlg = null;
        Msger(window.top).emit('reflash');
    })
    .regist('closeSettingTable', function(action) {
        if (settingTableDialog) {
        	 settingTableDialog.close();
        	 if (action == 'cancel') {
                 return;
             }
        	var gridId = 'datagrid_cbjymd';
      		var grid=mini.get(gridId);
      		var rows = grid.getSelecteds();
      		for(var i = 0 ; i< rows.length;i++){
      			var row = rows[i];
      			grid.removeRow(row, false)
      		}
      		saveOrder();
            setTimeout(function(){
              Msger(window.top).emit('reflash');
          	},1000)
          
        }
    })
    .regist('reflash', function(isGroupDel = false) {
    	var selectGroup='';
        var group = sider.get().emit('getSelected');
        if(!group){
        	selectGroup = defualtGroup;
        }else{
        	selectGroup = group.id;
        }
        Msger(window.top).emit('reloadList', [selectGroup,isGroupDel]);
        Msger(window.top).emit('reloadListDw');
        // Msger(window.top).emit('reload');
        // Msger(window.top).emit('reloadDw');
    })
    //保存会议信息
function saveMeet() {
	//debugger
    var MeetTime = mini.get("date1").text;
    //var MEndTime = mini.get("date2").text;
    //var content = $('#content').val();
    //console.log(MeetTime + '----------' + MEndTime + '----------' + content + '----------')
    var HYMC = MeetTime

    var meetInfo = '{ "meettime":" ' + MeetTime +
        '"}';

    EndMeetGrid.save(MeetTime, meetInfo, HYMC).then(function() {
    	zDialog.alert('保存动议酝酿成功');
    })['catch'](function(msg) {
    	zDialog.alert(msg)
    });

}
//结束会议
function over() {
    var MeetTime = mini.get("date1").text;
    if (!MeetTime || (MeetTime && MeetTime.trim() == "")) {
    	zDialog.alert('请填写动议时间！');
        return;
    }
    /*var MEndTime = mini.get("date2").text;
    if (!MEndTime || (MEndTime && MEndTime.trim() == "")) {
    	zDialog.alert('请填写领导同意时间！');
        return;
    }
    var content = $('#content').val();
    if (!content || (content && content.trim() == "")) {
    	zDialog.alert('请填写记录！');
        return;
    }*/
    var HYMC = MeetTime
    var meetInfo = '{ "meettime":" ' + MeetTime +
        '"}';
    var grid = mini.get("end_meet-grid");
	var rows = grid.getSelecteds();
	if (rows.length < 1) {
		zDialog.alert("请在列表中选择人员！");
		return;
	}
    var persons = EndMeetGrid.getSelectedData();
    console.log(persons)

    var flag = false;

    if (persons.length === 0) {
    	zDialog.alert('无上会人员，不能结束会议！');
        flag = true;
    } else {
        for (var i = 0; i <= persons.length - 1; i++) {
            if (persons[i].personId == '00000000-0000-0000-0000-000000000000' && persons[i].personDutys[0].nextMeet == '04') {
                flag = true;
                alert('空岗不能流转到讨论决定环节！');
                return;
            }
        }
    }

    if (flag) {
        return;
    }



    EndMeetGrid.over(MeetTime, meetInfo, persons, HYMC)
        .then(function() {
        	zDialog.alert('结束动议酝酿成功',function(){
                Msger().emit('init');
        	});
        })['catch'](function(msg) {
        	zDialog.alert(msg)
        });
}

function openDialog(title, url) {
    if (dlg) dlg.close();
    dlg = new zDialog(); // 定义Dialog对象
    dlg.Model = true;
    dlg.Width = 1000; // 定义长度
    dlg.Height = 500;
    dlg.URL = url;
    dlg.Title = title;
    dlg.CancelEvent = function() {
        dlg.close()
        dlg = null;
        Msger(window.top).emit('reflash');
    };
    dlg.show()
}

function getData() {
    var result = [];
    var bjqe2300 = getUrlParams('bjqe2300');
    api.getKcGroup(bjqe2300, '').then(function(data) {
        data.map(function(v) {
            var map1 = {};
            map1.id = v.bjqe2400;
            map1.text = v.bjqe2401;
            result.push(map1);
            return result;
        })
    })
}
// eg: 当页面加载完成的时候调用 init 事件
Shelf.done().then(function() {
        Msger().emit('init')
    })
    //得到url中的参数
function getUrlParams(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); // 构造一个含有目标参数的正则表达式对象
    var r = window.location.search.substr(1).match(reg); // 匹配目标参数
    if (r != null) {
        return unescape(r[2]);
    }
    return null; // 返回参数值
}
//删除记录
function cancel_btn() {
    mini.parse();
    var grid = mini.get("datagrid_cbjymd");
    var rows = grid.getSelecteds();
    var newArr = [];
    if (rows.length < 1) {
    	zDialog.alert("请先选中您要取消的记录！");
    } else {
    	zDialog.confirm("确定要取消登记吗",function(){
            for (var i = 0; i < rows.length; i++) {
                var obj = {};
                obj.A00 = rows[i].PERSONID;
                obj.BatchID = rows[i].BATCHID;
                obj.RecordIDs = rows[i].RECORDID;
                obj.flid = '03';
                newArr[i] = obj;

            }
            var jsonArr = JSON.stringify(newArr);
            var parameters = {
                "gridData": jsonArr
            };
            api.deleteRecord(parameters).then(function() {
            	zDialog.alert("取消成功！",function(){            		
            		var gridId = 'datagrid_cbjymd';
              		var grid=mini.get(gridId);
              		var rows = grid.getSelecteds();
              		for(var i = 0 ; i< rows.length;i++){
              			var row = rows[i];
              			grid.removeRow(row, false)
              		}
              		saveOrder();
                    setTimeout(function(){
                      Msger(window.top).emit('reflash');
                  	},1000)
            	});
            })
        })
    }
}
//移动后保存当前顺序
function saveOrder() {
    mini.parse();
    var grid = mini.get("datagrid_cbjymd");
    var d = grid.data;
    var newKGArr = [];
    var newSGArr = [];
    var kgCount = 0;
    var sgCount = 0;
    //分批次处理排序
    for (var i = 0; i < d.length; i++) {
    	if(d[i].PERSONID == "00000000-0000-0000-0000-000000000000"){
	        var obj = {};
	        obj.NUM = i+1; // 保存记录的pindex
	        obj.Pkvalue = d[i].RECORDID;
	        obj.tableName = 'BJQE05'; // 保存表名
	        obj.Pkname = 'RECORDID' // 保存主键
	        obj.orderColumn = 'BJQE0544' //排序列名
	        newKGArr[kgCount] = obj;
	        kgCount++;
    	}else{
    		var obj = {};
	        obj.NUM = i+1; // 保存记录的pindex
	        obj.Pkvalue = d[i].PERSONID;
	        obj.tableName = 'BJQE06'; // 保存表名
	        obj.Pkname = 'A00' // 保存主键
	        obj.orderColumn = 'BJQE0648' //排序列名
	        newSGArr[sgCount] = obj;
	        sgCount++;
    	}
    }
  
    var jsonKGArr = JSON.stringify(newKGArr);
    var jsonSGArr = JSON.stringify(newSGArr);
    var kgParameters = {
        "gridData": jsonKGArr
    };
    var sgParameters = {
            "gridData": jsonSGArr
        };
    // 发送保存数据ajax
    api.saveOrder(kgParameters);
    api.saveOrder(sgParameters);
}

function GetSelectionsDataByColName(grid, colName) {
    // 获取所有选中的行
    var records = grid.getSelecteds();
    if (!records || records.length == 0) {
    	zDialog.alert("请选择人员")
        return;
    }
    var allData = [];
    for (var i = 0; i < records.length; i++) {
        allData.push(records[i][colName]);
    }

    // 添加逗号
    return allData.join(",");
}
//调整分组
function showAdjustGroup(A00s, oGroupId, batchIds, recordIds) {
    if (dialog) dialog.close()
    var dialog = new zDialog();
    dialog.Modal = true;
    dialog.URL = "/gbrm/common/rywh/tzfz.html?meetType=" + meetType + "&A00s=" + A00s + "&oGroupId=" + oGroupId + "&BatchIds=" + batchIds + "&RecordIds=" + recordIds;
    dialog.Width = 836;
    dialog.Height = 320;
    dialog.Top = "50%";
    dialog.Left = "50%";
    dialog.Title = "分组分组";
    dialog.show();
    settingTableDialog = dialog
}
//调整分组
function adjust_btn() {
    mini.parse();
    var grid = mini.get("datagrid_cbjymd");
    var rows = grid.getSelecteds();
    var A00s = [];
    var recordIds = [];
    var batchIds = [];
    var oGroupId = ""
    if (rows.length > 0) {
        for (var i = 0; i < rows.length; i++) {
            A00s[i] = rows[i].PERSONID
            oGroupId = rows[i].GROUPID
            recordIds[i] = rows[i].RECORDID
            batchIds[i] = rows[i].BATCHID
        }
        showAdjustGroup(A00s, oGroupId, batchIds, recordIds)
    } else {
    	zDialog.alert("请在列表中选择人员!")
    }
}
//上移
function up_btn() {
    mini.parse();
    var grid = mini.get("datagrid_cbjymd");
    var rows = grid.getSelecteds();
    if (rows.length > 0) {
        for (var i = 0; i < rows.length; i++) {
            var row = rows[i];
            if (row) {
                var index = grid.indexOf(row);
                // 判断上移到顶部
                if (index >= 1) {
                    row.NUM = index;
                    grid.moveRow(row, index - 1);
                    grid.getRow(index).NUM = index + 1;
                } else {
                	zDialog.alert("已在最顶部。");
                    return;
                }
            }
        }
        saveOrder(); // 移动后保存当前顺序
    } else {
    	zDialog.alert("请先选择您要移动的记录！");
    }
}
//下移
function down_btn() {
    mini.parse();
    var grid = mini.get("datagrid_cbjymd");
    var rows = grid.getSelecteds();
    if (rows.length > 0) {
        for (var i = rows.length - 1; i >= 0; i--) {
            var row = rows[i];
            if (row) {
                var index = grid.indexOf(row);
                // 当前页的总记录数
                var data = grid.data;
                // 判断下移到底部
                if (index >= data.length - 1) {
                	zDialog.alert("已在最底部");
                    return;
                } else {
                    row.NUM = index + 2;
                    grid.moveRow(row, index + 2);
                    grid.getRow(index).NUM = index + 1;
                }
            }
        }
        saveOrder();
    } else {
    	zDialog.alert("请先选择您要移动的记录！");
    }
}

function modifyDuty() {
    mini.parse();
    var grid = mini.get("datagrid_cbjymd");
    var rows = grid.getSelecteds();
    var newArr = [];
    if (rows.length < 1) {
    	zDialog.alert("请在列表中选择人员！");
    } else if (rows.length > 1) {
    	zDialog.alert("请在列表中选择一位人员！");
    } else {
        var tempA00 = rows[0].PERSONID
            //var url ="/gbrm/rydj/zwtz.html?A00="+tempA00+"&meetType=CBGT"+"&meetType=CBGT"+"&flid=02"
        var url = "/gbrm/rydj/zwtz.html?A00=" + tempA00 + "&meetType=" + meetType + "&flid=03"
        openDialog("职务调整", url);
    }
}

function openNewRMDJ() {
    openDialog('职务登记', '/gbrm/fxypdy/rzdj/main/rzdjmain.html?meetType=CBGT&BatchId=' + batchId)
}

function openRMDJ() {
    openDialog('职务登记', '/gbrm/fxypdy/djdetail.html?meetType=CBGT&BatchId=' + batchId)
}
//职务调整
function adjustDutyNew() {
    mini.parse();
    var grid = mini.get("datagrid_cbjymd");
    var rows = grid.getSelecteds();
    var newArr = [];
    if (rows.length < 1) {
    	zDialog.alert("请在列表中选择人员！");
    } else if (rows.length > 1) {
    	zDialog.alert("请在列表中选择一位人员！");
    } else {
        var tempA00 = rows[0].PERSONID
        var batchId = rows[0].BATCHID
        var recordId = rows[0].RECORDID
        openDialog('职务登记', '/gbrm/fxypdy/rzdj/main/rzdjmain.html?A00=' + tempA00 + '&meetType=CBGT&BatchId=' + batchId + '&RecordID=' + recordId)
    }
}
//职务调整
function adjustDuty() {
    mini.parse();
    var grid = mini.get("datagrid_cbjymd");
    var rows = grid.getSelecteds();
    var newArr = [];
    if (rows.length < 1) {
    	zDialog.alert("请在列表中选择人员！");
    } else if (rows.length > 1) {
    	zDialog.alert("请在列表中选择一位人员！");
    } else {
        var tempA00 = rows[0].PERSONID
        var batchId = rows[0].BATCHID
        var recordId = rows[0].RECORDID
            //		if(tempA00 == '00000000-0000-0000-0000-000000000000'){
        openDialog('职务登记', '/gbrm/fxypdy/djdetail.html?A00=' + tempA00 + '&meetType=CBGT&BatchId=' + batchId + '&RecordID=' + recordId)
            //		}else{
            //			alert("请在列表中选择空岗记录！");
            //		}

    }
}