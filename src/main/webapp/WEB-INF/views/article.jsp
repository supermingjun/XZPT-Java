<%--
  Created by IntelliJ IDEA.
  User: Murphy
  Date: 2019/5/19
  Time: 18:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <!--
  __   _____  _    _
 / _| /__  / | |  | |
| |_    / /  | |  | |
|  _/  / /   | |  | |
| |   / /__  | \__/ |
|_|  /____/   \____/

-->
    <meta content="text/html; charset=utf-8" http-equiv="Content-Type">
    <meta content="IE=edge" http-equiv="X-UA-Compatible">
    <meta content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=0,viewport-fit=cover"
          name="viewport">
    <meta content="yes" name="apple-mobile-web-app-capable">
    <meta content="black" name="apple-mobile-web-app-status-bar-style">
    <meta content="telephone=no" name="format-detection">
    <title>
        ${title}
    </title>
    <style>
        .radius_avatar img {
            display: block;
            width: 100%;
            height: 100%;
            border-radius: 50%;
            -moz-border-radius: 50%;
            -webkit-border-radius: 50%;
            background-color: #eee
        }

        .rich_media_inner {
            word-wrap: break-word;
            -webkit-hyphens: auto;
            -ms-hyphens: auto;
            hyphens: auto
        }

        .rich_media_area_primary {
            padding: 20px 16px 12px;
            background-color: #fafafa
        }

        html {
            -ms-text-size-adjust: 100%;
            -webkit-text-size-adjust: 100%;
            line-height: 1.6
        }

        body {
            -webkit-touch-callout: none;
            font-family: -apple-system-font, BlinkMacSystemFont, "Helvetica Neue", "PingFang SC", "Hiragino Sans GB", "Microsoft YaHei UI", "Microsoft YaHei", Arial, sans-serif;
            color: #333;
            background-color: #f2f2f2;
            letter-spacing: .034em
        }

        h1, h2, h3, h4, h5, h6 {
            font-weight: 400;
            font-size: 16px
        }

        * {
            margin: 0;
            padding: 0
        }

        a {
            color: #576b95;
            text-decoration: none;
            -webkit-tap-highlight-color: rgba(0, 0, 0, 0)
        }

        .rich_media_title {
            font-size: 22px;
            line-height: 1.4;
            margin-bottom: 14px
        }

        @supports (-webkit-overflow-scrolling:touch) {
            .rich_media_title {
                font-weight: 700
            }
        }

        .rich_media_meta_list {
            margin-bottom: 22px;
            line-height: 20px;
            font-size: 0;
            word-wrap: break-word;
            word-break: break-all
        }

        .rich_media_meta_list em {
            font-style: normal
        }

        .rich_media_meta {
            display: inline-block;
            vertical-align: middle;
            margin: 0 10px 10px 0;
            font-size: 15px;
            -webkit-tap-highlight-color: rgba(0, 0, 0, 0)
        }

        .rich_media_meta.meta_tag_text {
            margin-right: 0
        }


        .meta_enterprise_tag img {
            width: 30px;
            height: 30px !important;
            display: block;
            position: relative;
            margin-top: -3px;
            border: 0
        }

        .rich_media_meta_text {
            color: rgba(0, 0, 0, 0.3)
        }

        .rich_media_content {
            text-indent: 2em;
            white-space: pre-wrap;
            overflow: hidden;
            color: #333;
            font-size: 17px;
            word-wrap: break-word;
            -webkit-hyphens: auto;
            -ms-hyphens: auto;
            hyphens: auto;
            text-align: justify;
            position: relative;
            z-index: 0
        }

        .rich_media_content * {
            max-width: 100% !important;
            box-sizing: border-box !important;
            -webkit-box-sizing: border-box !important;
            word-wrap: break-word !important
        }

        .rich_media_content p {
            clear: both;
            min-height: 1em
        }

        .rich_media_content em {
            font-style: italic
        }

        .rich_media_content fieldset {
            min-width: 0
        }


        .rich_media_content .code-snippet *, .rich_media_content .code-snippet__fix * {
            max-width: 1000% !important
        }

        img {
            height: auto !important
        }


        @media only screen and (device-width: 375px) and (device-height: 812px) and (-webkit-device-pixel-ratio: 3) and (orientation: landscape) {
            .rich_media_area_primary {
                padding: 20px 60px 15px 60px
            }
        }

        @media screen and (min-width: 1024px) {
            .rich_media_area_primary_inner {
                max-width: 677px;
                margin-left: auto;
                margin-right: auto
            }

            .rich_media_area_primary {
                padding-top: 32px
            }
        }

        blockquote {
            padding-left: 10px;
            border-left: 3px solid #dbdbdb;
            color: rgba(0, 0, 0, 0.5);
            font-size: 15px;
            padding-top: 4px;
            margin: 1em 0
        }

        .qa__card .qa__item-info-avatar img {
            width: 100%
        }

        .code-snippet code {
            text-align: left;
            font-size: 14px;
            display: block;
            white-space: pre-wrap;
            position: relative;
            font-family: Consolas, "Liberation Mono", Menlo, Courier, monospace
        }

        .code-snippet code::before {
            position: absolute;
            min-width: 1.5em;
            text-align: right;
            left: -2.5em;
            counter-increment: line;
            content: counter(line);
            display: inline;
            margin-right: 12px;
            color: rgba(0, 0, 0, 0.15)
        }

        .code-snippet_nowrap code {
            white-space: pre;
            display: flex
        }

        .code-snippet__fix pre {
            overflow-x: auto;
            padding: 1em;
            padding-left: 0;
            white-space: normal;
            flex: 1;
            -webkit-overflow-scrolling: touch
        }

        .code-snippet__fix code {
            text-align: left;
            font-size: 14px;
            display: block;
            white-space: pre;
            display: flex;
            position: relative;
            font-family: Consolas, "Liberation Mono", Menlo, Courier, monospace
        }

        .code-snippet__fix .code-snippet__line-index li {
            list-style-type: none;
            text-align: right
        }

        .code-snippet__fix .code-snippet__line-index li::before {
            min-width: 1.5em;
            text-align: right;
            left: -2.5em;
            counter-increment: line;
            content: counter(line);
            display: inline;
            color: rgba(0, 0, 0, 0.15)
        }

        .original_tool_area .radius_avatar img {
            height: 100% !important
        }

        .preview_appmsg .rich_media_title {
            margin-top: 2.3em
        }

        @media screen and (min-width: 1024px) {
            .preview_appmsg .rich_media_title.rich_media_title {
                margin-top: 0
            }
        }


        .preview_appmsg .rich_media_title {
            margin-top: 1.5em
        }

        .account_info .radius_avatar img {
            background-color: transparent
        }

        .original_panel_tool a {
            color: #576b95
        }


        .appmsg_skin_default .rich_media_area_primary {
            background-color: #fff
        }

        .top_banner {
            display: flex;
            align-items: center;
            justify-content: center;
        }

    </style>

</head>
<body class="appmsg_skin_default">

<div class="rich_media">
    <div class="top_banner">
        <img height="56" src="https://serve.wangmingjun.top/app/xzpt/logo_fzu.png" width="195">
    </div>
    <div class="rich_media_inner">
        <div class="rich_media_area_primary">
            <div class="rich_media_area_primary_inner">
                <div>
                    <h2 class="rich_media_title">
                        Google I/O 2019
                    </h2>
                    <div class="rich_media_meta_list">
                        <span class="rich_media_meta rich_media_meta_text meta_tag_text">
                            发布者：
                        </span>
                        <span class="rich_media_meta rich_media_meta_text">
                            Google
                        </span>
                        <span class="rich_media_meta rich_media_meta_text meta_tag_text">
                            发布时间：
                        </span>
                        <span class="rich_media_meta rich_media_meta_text">
                            2019年9月9日
                        </span>
                    </div>
                    <div class="rich_media_content ">
                        ${content}
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
