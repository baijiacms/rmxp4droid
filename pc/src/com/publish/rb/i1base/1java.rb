require 'java'

  
for s in ['Ycore','Rect','YInput','Viewport','Tone','Audio','YGraphics','Color','Font','Sprites','Bitmap','Rmxp4droid']
  java_import 'com.rmxp4droid.pub.component.'+s
end

#set basics
$debug = false