---
layout: page
title: About
permalink: /about/
published: true
---

<div class="page" markdown="1">

{% capture page_subtitle %}
<img
    class="me"
    alt="{{ author.name }}"
/>
{% endcapture %}

{% include page/title.html title=page.title subtitle=page_subtitle %}

## Yet another Java game engine

Joe3D is a custom 3D game engine developed by a Swedish computer science student based in Helsingborg. It is written in Java using the OpenGL API for rendering.

You can find the source code for Joe3D at Github: [Nilsiker](https://github.com/Nilsiker) / [joe3d](https://github.com/Nilsiker/joe3d)

</div>
