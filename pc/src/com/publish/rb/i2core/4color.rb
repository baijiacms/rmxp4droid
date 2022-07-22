# http://www.hbgames.org/forums/viewtopic.php?f=11&t=49838&start=0&hilit=RGSS+Table+Class

class Color
  def _dump(d = 0)
     [@red, @green, @blue, @alpha].pack('d4')
  end
  def self._load(s)
     Color.new(*s.unpack('d4'))
  end
# def set(r, g, b, a = 255)
#    @red = r
#    @green = g
#    @blue = b
#    @alpha = a
# end
end