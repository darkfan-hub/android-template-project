package com.darkfan.repo.permission;

/**
 * @author Fanfan Gu <a href="mailto:stefan.gufan@gmail.com">Contact me.</a>
 * @date 25/04/2022 00:59
 * @desc 权限设置页结果回调接口
 * 参考: https://github.com/getActivity/XXPermissions
 */
public interface OnPermissionPageCallback {

    /**
     * 权限已经授予
     */
    void onGranted();

    /**
     * 权限已经拒绝
     */
    void onDenied();
}