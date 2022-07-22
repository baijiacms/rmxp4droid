# http://www.hbgames.org/forums/viewtopic.php?f=11&t=49838&start=0&hilit=RGSS+Table+Class

class Tone
  def set(r, g, b, a = 0)
     @red = r
     @green = g
     @blue = b
     @gray = a
  end
  def _dump(d = 0)
     [@red, @green, @blue, @gray].pack('d4')
  end
  def self._load(s)
     Tone.new(*s.unpack('d4'))
  end
end