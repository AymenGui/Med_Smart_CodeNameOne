package com.diamonddevgroup.utils;


/**
 *  <p>
 *  A border that can either be a circle or a circular rectangle which is a
 *  rectangle whose sides are circles. This border can optionally have a drop
 *  shadow associated with it.</p>
 *  <script src="https://gist.github.com/codenameone/3e91e5eab4e677e6b03962e78ae99e07.js"></script>
 *  <img src="https://www.codenameone.com/img/blog/round-border.png" alt="Round Border" />
 * 
 * 
 *  @author Shai Almog
 */
public class RoundedBorder extends com.codename1.ui.plaf.Border {

	/**
	 *  Creates a flat round border with no stroke and no shadow and the default
	 *  color, this call can be chained with the other calls to mutate the
	 *  color/opacity etc.
	 * 
	 *  @return a border instance
	 */
	public static RoundedBorder create() {
	}

	/**
	 *  <p>
	 *  Uses the style of the components UIID to draw the background of the
	 *  border, this effectively overrides all other style settings but allows
	 *  the full power of UIID drawing including gradients, background images
	 *  etc.</p>
	 *  <p>
	 *  <strong>Notice: </strong>this flag will only work when shaped clipping is
	 *  supported. That feature isn't available in all platforms...</p>
	 * 
	 * 
	 *  @param uiid true to use the background of the component setting
	 *  @return border instance so these calls can be chained
	 */
	public RoundedBorder uiid(boolean uiid) {
	}

	/**
	 *  True is we use the background of the component setting to draw
	 * 
	 *  @return true if we draw based on the component UIID
	 */
	public boolean getUIID() {
	}

	/**
	 *  Sets the background color of the circle/rectangle
	 * 
	 *  @param color the color
	 *  @return border instance so these calls can be chained
	 */
	public RoundedBorder color(int color) {
	}

	/**
	 *  Sets the background opacity of the circle/rectangle
	 * 
	 *  @param opacity the background opacity from 0-255 where 255 is completely
	 *  opaque
	 *  @return border instance so these calls can be chained
	 */
	public RoundedBorder opacity(int opacity) {
	}

	/**
	 *  Sets the opacity of the stroke line around the circle/rectangle
	 * 
	 *  @param strokeOpacity the opacity from 0-255 where 255 is completely
	 *  opaque
	 *  @return border instance so these calls can be chained
	 */
	public RoundedBorder strokeOpacity(int strokeOpacity) {
	}

	/**
	 *  Sets the stroke color of the circle/rectangle
	 * 
	 *  @param strokeColor the color
	 *  @return border instance so these calls can be chained
	 */
	public RoundedBorder strokeColor(int strokeColor) {
	}

	/**
	 *  Sets the stroke of the circle/rectangle
	 * 
	 *  @param stroke the stroke object
	 *  @return border instance so these calls can be chained
	 */
	public RoundedBorder stroke(com.codename1.ui.Stroke stroke) {
	}

	/**
	 *  Sets the stroke of the circle/rectangle
	 * 
	 *  @param stroke the thickness of the stroke object
	 *  @param mm set to true to indicate the value is in millimeters, false
	 *  indicates pixels
	 *  @return border instance so these calls can be chained
	 */
	public RoundedBorder stroke(float stroke, boolean mm) {
	}

	/**
	 *  Sets the spread in pixels of the shadow i.e how much bigger is it than
	 *  the actual circle/rectangle
	 * 
	 *  @param shadowSpread the amount in pixels representing the size of the
	 *  shadow
	 *  @param mm set to true to indicate the value is in millimeters, false
	 *  indicates pixels
	 *  @return border instance so these calls can be chained
	 */
	public RoundedBorder shadowSpread(int shadowSpread, boolean mm) {
	}

	/**
	 *  Sets the spread in pixels of the shadow i.e how much bigger is it than
	 *  the actual circle/rectangle
	 * 
	 *  @param shadowSpread the amount in pixels representing the size of the
	 *  shadow
	 *  @return border instance so these calls can be chained
	 */
	public RoundedBorder shadowSpread(int shadowSpread) {
	}

	/**
	 *  Sets the opacity of the shadow from 0 - 255 where 0 means no shadow and
	 *  255 means opaque black shadow
	 * 
	 *  @param shadowOpacity the opacity of the shadow
	 *  @return border instance so these calls can be chained
	 */
	public RoundedBorder shadowOpacity(int shadowOpacity) {
	}

	/**
	 *  Sets the position of the rounded corner to one of all, top, bottom, left,
	 *  or right
	 * 
	 *  @param roundedPosition the position of the rounded corners
	 *  @return border instance so these calls can be chained
	 */
	public RoundedBorder roundedPosition(int roundedPosition) {
	}

	/**
	 *  Sets the radius of the rounded corners to specified value
	 * 
	 *  @param radius the radius of the rounded corners
	 *  @return border instance so these calls can be chained
	 */
	public RoundedBorder radius(int radius) {
	}

	/**
	 *  The position of the shadow on the X axis where 0.5f means the center and
	 *  higher values draw it to the right side
	 * 
	 *  @param shadowX the position of the shadow between 0 - 1 where 0 equals
	 *  left and 1 equals right
	 *  @return border instance so these calls can be chained
	 */
	public RoundedBorder shadowX(float shadowX) {
	}

	/**
	 *  The position of the shadow on the Y axis where 0.5f means the center and
	 *  higher values draw it to the bottom
	 * 
	 *  @param shadowY the position of the shadow between 0 - 1 where 0 equals
	 *  top and 1 equals bottom
	 *  @return border instance so these calls can be chained
	 */
	public RoundedBorder shadowY(float shadowY) {
	}

	/**
	 *  The blur on the shadow this is the standard Gaussian blur radius
	 * 
	 *  @param shadowBlur The blur on the shadow this is the standard Gaussian
	 *  blur radius
	 *  @return border instance so these calls can be chained
	 */
	public RoundedBorder shadowBlur(float shadowBlur) {
	}

	/**
	 *  When set to true this border grows into a rectangle when the space isn't
	 *  perfectly circular
	 * 
	 *  @param rectangle When set to true this border grows into a rectangle when
	 *  the space isn't perfectly circular
	 *  @return border instance so these calls can be chained
	 */
	public RoundedBorder rectangle(boolean rectangle) {
	}

	@java.lang.Override
	public void paintBorderBackground(com.codename1.ui.Graphics g, com.codename1.ui.Component c) {
	}

	@java.lang.Override
	public int getMinimumHeight() {
	}

	@java.lang.Override
	public int getMinimumWidth() {
	}

	@java.lang.Override
	public boolean isBackgroundPainter() {
	}

	/**
	 *  The color of the border background
	 * 
	 *  @return the color
	 */
	public int getColor() {
	}

	/**
	 *  The opacity (transparency) of the border background
	 * 
	 *  @return the opacity
	 */
	public int getOpacity() {
	}

	/**
	 *  The color of the edge of the border if applicable
	 * 
	 *  @return the strokeColor
	 */
	public int getStrokeColor() {
	}

	/**
	 *  The opacity of the edge of the border if applicable
	 * 
	 *  @return the strokeOpacity
	 */
	public int getStrokeOpacity() {
	}

	/**
	 *  The thickness of the edge of the border if applicable, 0 if no stroke is
	 *  needed
	 * 
	 *  @return the strokeThickness
	 */
	public float getStrokeThickness() {
	}

	/**
	 *  True if the thickness of the stroke is in millimeters
	 * 
	 *  @return the strokeMM
	 */
	public boolean isStrokeMM() {
	}

	/**
	 *  The spread of the shadow in pixels of millimeters
	 * 
	 *  @return the shadowSpread
	 */
	public int getShadowSpread() {
	}

	/**
	 *  The opacity of the shadow between 0 and 255
	 * 
	 *  @return the shadowOpacity
	 */
	public int getShadowOpacity() {
	}

	/**
	 *  X axis bias of the shadow between 0 and 1 where 0 is to the top and 1 is
	 *  to the bottom, defaults to 0.5
	 * 
	 *  @return the shadowX
	 */
	public float getShadowX() {
	}

	/**
	 *  Y axis bias of the shadow between 0 and 1 where 0 is to the left and 1 is
	 *  to the right, defaults to 0.5
	 * 
	 *  @return the shadowY
	 */
	public float getShadowY() {
	}

	/**
	 *  The Gaussian blur size
	 * 
	 *  @return the shadowBlur
	 */
	public float getShadowBlur() {
	}

	/**
	 *  True if the shadow spread is in millimeters
	 * 
	 *  @return the shadowMM
	 */
	public boolean isShadowMM() {
	}

	/**
	 *  True if this border grows into a rectangle horizontally or keeps growing
	 *  as a circle
	 * 
	 *  @return the rectangle
	 */
	public boolean isRectangle() {
	}

	@java.lang.Override
	public int hashCode() {
	}

	@java.lang.Override
	public boolean equals(Object obj) {
	}
}
