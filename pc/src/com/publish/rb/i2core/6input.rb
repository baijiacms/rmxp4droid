# Wrapper for de.yaams.jrgss.core.java.YInput

module Input
  # -- Constants
  DOWN = YInput.DOWN
  LEFT = YInput.LEFT
  RIGHT = YInput.RIGHT
  UP = YInput.UP

  A = YInput.A
  B = YInput.B
  C = YInput.C
  X = YInput.X
  Y = YInput.Y
  Z = YInput.Z
  L = YInput.L
  R = YInput.R

  SHIFT = YInput.SHIFT
  CTRL = YInput.CTRL
  ALT = YInput.ALT

  F5 = YInput.F5
  F6 = YInput.F6
  F7 = YInput.F7
  F8 = YInput.F8
  F9 = YInput.F9
  
  # -- Methods
  def Input.repeat?(num)
    YInput.isRepeated(num)
  end
  def Input.trigger?(num)
    YInput.isTrigger(num)
  end
  def Input.press?(num)
    YInput.isPressed(num)
  end
  def Input.update
	YInput.update
  end
  def Input.dir4
    YInput.dir4
  end
  def Input.dir8
    YInput.dir8
  end
end