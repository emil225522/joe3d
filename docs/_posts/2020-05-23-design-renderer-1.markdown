---
layout: post
title:  "Design: Renderer #1"
date:   2020-05-23 15:18:52 +0200
categories: general
# `` highlight
#   {% highlight <lang>%}
#   {% endhighlight %}
# <!–-break-–> - read more marker
---

Yesterday I did some bedside reading of [Game Engine Architecture](https://www.gameenginebook.com/) by Jason Gregory and realized I'll have to rewind the tape a bit. 

In the [last post](https://nilsiker.github.io/joe3d/posts/hello-world) I showed off some transformed meshes with materials. That is basically just a static, glorified model viewer. In order to actually make this a rendering system in a game engine I have to do better. And it turns out it won't be easy, but who would've thought, right?

**Let's start out with pinpointing a few basic system specifications. Listed below is what I expect the rendering engine to be capable of:**
<!–-break-–>

+ Render an arbitrary amount of `meshes`.
+ Take into account the world `position` of the mesh
+ Take into account the `material` of the mesh
+ Model `lighting` after the [Blinn-Phong reflection model](https://en.wikipedia.org/wiki/Blinn%E2%80%93Phong_reflection_model)
+ Add and remove content from vertex buffers, at `runtime`

**And, explicitly, what the renderer doesn't have to be capable of:**

* Have support for multiple shader programs
* Handle VBO indexing
* _... yeah, pinpointing this was way harder than I anticipated!_

Naturally the needs and need-nots may come to change, but for now let's settle for this!

Taking a leap of faith from my current implementation, I end up with the following <a href="{{site.baseurl}}/assets/img/rendering01.png" target="_blank">UML class diagram</a>.
![Rendering UML]({{site.baseurl}}/assets/img/rendering01.png)

Now that we have a rough draft, we are all set to start reworking how the rendering system handles OpenGL VAOs and VBOs. So make sure to stay tuned, because the next part'll have some crazy exciting code snippets! `Wow!`

And let's hope this design won't bite me in the arse too hard further down the line.

Take care, y'all!



