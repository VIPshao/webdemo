<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>queryPostList</title>
<%@include file="../includes/commonHead.jsp" %>

<script type="text/javascript" >
var treeGrid = null;
$(document).ready(function() {
	treeGrid = $('#treeGrid').treegrid({
		url : '${contextPath}/post/queryPostList.action',
		idField : 'id',
		parentField : 'parentId',
		treeField : 'name',
		fit : true,
		fitColumns : true,
		border : false,
		frozenColumns : [ [ {
			title : '组织id',
			field : 'id',
			width : 150,
			hidden : true
		},{
			field : 'parentId',
			title : '上级组织ID',
			width : 150,
			hidden : true
		}] ],
		columns : [ [ {
			field : 'name',
			title : '组织名称',
			width : 200
		}, {
			field : 'code',
			title : '组织编号',
			width : 230
		}, {
			field : 'fullName',
			title : '组织全称',
			width : 150
		}, {
			field : 'alias',
			title : '组织别名',
			width : 80,
			hidden : true
		}, {
			field : 'address',
			title : '地址',
			width : 40,
			hidden : true
		}, {
			field : 'fullAddress',
			title : '详细地址',
			width : 80,
			hidden : true
		}, {
			field : 'action',
			title : '操作',
			width : 50,
			formatter : function(value, row, index) {
				var str = '';
				if ($.canEdit) {
					str += $.formatString('<img onclick="editFun(\'{0}\');" src="{1}" title="编辑"/>', row.id, '${pageContext.request.contextPath}/style/images/extjs_icons/pencil.png');
				}
				str += '&nbsp;';
				if ($.canDelete) {
					str += $.formatString('<img onclick="deleteFun(\'{0}\');" src="{1}" title="删除"/>', row.id, '${pageContext.request.contextPath}/style/images/extjs_icons/cancel.png');
				}
				return str;
			}
		}, {
			field : 'remark',
			title : '备注',
			width : 200
		} ] ],
		toolbar : '#toolbar',
		onContextMenu : function(e, row) {
			e.preventDefault();
			$(this).treegrid('unselectAll');
			$(this).treegrid('select', row.id);
			$('#menu').menu('show', {
				left : e.pageX,
				top : e.pageY
			});
		},
		onLoadSuccess : function() {
			parent.$.messager.progress('close');
			$(this).treegrid('tooltip');
		}
	});
});
function redo() {
	var node = treeGrid.treegrid('getSelected');
	if (node) {
		treeGrid.treegrid('expandAll', node.id);
	} else {
		treeGrid.treegrid('expandAll');
	}
}

function undo() {
	var node = treeGrid.treegrid('getSelected');
	if (node) {
		treeGrid.treegrid('collapseAll', node.id);
	} else {
		treeGrid.treegrid('collapseAll');
	}
}
</script>
</head>
<body>
	<div class="easyui-layout" data-options="fit:true,border:false">
		<div data-options="region:'center',border:false" title="" style="overflow: hidden;">
			<table id="treeGrid" style="width:fit;height:fit"></table>
		</div>
	</div>
	
	<div id="toolbar" style="display: none;">
		<c:if test="${false}">
			<a onclick="addFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'pencil_add'">添加</a>
		</c:if>
		<a onclick="redo();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'resultset_next'">展开</a> 
		<a onclick="undo();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'resultset_previous'">折叠</a> 
		<a onclick="treeGrid.treegrid('reload');" href="javascript:void(0);" 
			class="easyui-linkbutton" data-options="plain:true,iconCls:'transmit'">刷新</a>
	</div>

	<div id="menu" class="easyui-menu" style="width: 120px; display: none;">
		<c:if test="${true}">
			<div onclick="addFun();" data-options="iconCls:'pencil_add'">增加</div>
		</c:if>
		<c:if test="${true}">
			<div onclick="deleteFun();" data-options="iconCls:'pencil_delete'">删除</div>
		</c:if>
		<c:if test="${true}">
			<div onclick="editFun();" data-options="iconCls:'pencil'">编辑</div>
		</c:if>
	</div>
</body>