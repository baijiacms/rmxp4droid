# http://houseslasher.com/index.php?showtopic=404

#==============================================================================
# ■ SG::Plane
#------------------
# by Selwyn
#==============================================================================
module SG; end

class Plane
 attr_reader :ox, :oy
 #--------------
 # ● initialize
 #--------------
 def initialize(viewport = nil)
   @sprite = Sprite.new(viewport)
   @max_width = Ycore.getWidth()
   @max_height = Ycore.getHeight()
   @bitmap = nil
   @ox = 0
   @oy = 0
 end
 #--------------
 # ● z, zoom_x, zoom_y, opacity, blend_type, color, tone
 #    z=, zoom_x=, zoom_y=, opacity=, blend_type=, color=, tone=
 #--------------
 def method_missing(symbol, *args)
   @sprite.method(symbol).call(*args)
 end
 #--------------
 # ● bitmap=
 #--------------
 def bitmap=(bitmap)
   @bitmap = bitmap
   refresh
 end
 #--------------
 # ● ox=
 #--------------
 def ox=(ox)
   w = @sprite.viewport != nil ? @sprite.viewport.rect.width : @max_width
   @ox = ox % w
   @sprite.ox = @ox
 end
 #--------------
 # ● oy=
 #--------------
 def oy=(oy)
   h = @sprite.viewport != nil ? @sprite.viewport.rect.height : @max_height
   @oy = oy % h
   @sprite.oy = @oy
 end
 #--------------
 # ● refresh
 #--------------
 def refresh
   return if @bitmap.nil?
   w = @sprite.viewport != nil ? @sprite.viewport.rect.width : @max_width
   h = @sprite.viewport != nil ? @sprite.viewport.rect.height : @max_height
   if @sprite.bitmap != nil
     @sprite.bitmap.dispose
   end
   @sprite.bitmap = Bitmap.new(w * 1.5, h * 1.5)
   max_x = w / @bitmap.width
   max_y = h / @bitmap.height
   for x in 0..max_x
     for y in 0..max_y
       @sprite.bitmap.blt(x * @bitmap.width, y * @bitmap.height,
        @bitmap, Rect.new(0, 0, @bitmap.width, @bitmap.height))
     end
   end
   for i in 1...4
     x = i % 2 * w
     y = i / 2 * h
     @sprite.bitmap.blt(x, y, @sprite.bitmap, Rect.new(0, 0, w, h))
   end
 end
end