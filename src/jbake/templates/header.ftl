<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8"/>
    <title><#if (content.title)??><#escape x as x?xml>${content.title} « R4S11</#escape><#else>Rahul Somasunderam's Blog</#if></title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <meta name="keywords" content="">
    <meta name="generator" content="JBake">

    <!-- Le styles -->
    <link href="<#if (content.rootpath)??>${content.rootpath}<#else></#if>css/bootstrap.min.css" rel="stylesheet">
    <link href="<#if (content.rootpath)??>${content.rootpath}<#else></#if>css/asciidoctor.css" rel="stylesheet">
    <link href="<#if (content.rootpath)??>${content.rootpath}<#else></#if>css/base.css" rel="stylesheet">
    <link href="<#if (content.rootpath)??>${content.rootpath}<#else></#if>css/prettify.css" rel="stylesheet">

    <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
      <script src="<#if (content.rootpath)??>${content.rootpath}<#else></#if>js/html5shiv.min.js"></script>
    <![endif]-->

    <!-- Fav and touch icons -->
    <link rel="apple-touch-icon-precomposed" sizes="144x144"
            href="<#if (content.rootpath)??>${content.rootpath}<#else></#if>ico/apple-touch-icon-144-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="114x114"
            href="<#if (content.rootpath)??>${content.rootpath}<#else></#if>ico/apple-touch-icon-114-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="72x72"
            href="<#if (content.rootpath)??>${content.rootpath}<#else></#if>ico/apple-touch-icon-72-precomposed.png">
    <link rel="apple-touch-icon-precomposed"
            href="<#if (content.rootpath)??>${content.rootpath}<#else></#if>ico/apple-touch-icon-57-precomposed.png">
    <link rel="shortcut icon" href="<#if (content.rootpath)??>${content.rootpath}<#else></#if>favicon.ico">

    <link rel="alternate" type="application/rss+xml" title="RSS Feed for petefreitag.com" href="/feed.xml" />
  </head>
  <body onload="prettyPrint()">
    <div id="wrap">
        <div class="container">
            <div class="row">
                <div class="col-md-3">
                    <header>
                      <a class="brand" href="<#if (content.rootpath)??>${content.rootpath}<#else></#if>index.html">
                        <h1>Rahul Somasunderam</h1>
                      </a>

                      <p>Programmer, Cyclist, Trivia Junkie.</p>

                      <h1>
                          <a href="http://github.com/rahulsom"><i class="icon-github" aria-hidden="true" alt="Github"></i></a>
                          <a href="http://twitter.com/rahulsom"><i class="icon-twitter" aria-hidden="true" alt="Twitter"></i></a>
                          <a href="http://linkedin.com/in/rahulsom"><i class="icon-linkedin" aria-hidden="true" alt="LinkedIn"></i></a>
                          <a href="http://stackoverflow.com/users/586134/rahul"><i class="icon-stack-overflow" aria-hidden="true" alt="Stack Overflow"></i></a>

                      </h1>
                      <br>
                      <p></p>

                      <ul class="pages">
                        <li><a href="<#if (content.rootpath)??>${content.rootpath}<#else></#if>about.html">About</a></li>
                        <li><a href="<#if (content.rootpath)??>${content.rootpath}<#else></#if>${config.archive_file}">Archive</a></li>
                        <li><a href="<#if (content.rootpath)??>${content.rootpath}<#else></#if>${config.feed_file}">Subscribe</a></li>
                        <!-- <li><a href="/resume.html">Resume</a></li> -->
                      </ul>
                    </header>
                </div>
                <div class="col-md-9">
