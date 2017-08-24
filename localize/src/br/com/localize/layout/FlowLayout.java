package br.com.localize.layout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * <p>
 * <b>Title:</b> FlowLayout
 * </p>
 * 
 * <p>
 * <b>Description:</b> Classe responsável por manipular os elementos da tela para quebrar a linha quando necessário.
 * </p>
 * 
 * <p>
 * <b>Company: </b> FASAM
 * </p>
 * 
 * @author Silvânio Júnior
 * 
 * @version 1.0.0
 */
public class FlowLayout extends ViewGroup {

	/** Atributo lineHeight. */
	private int	lineHeight;

	/**
	 * Responsável pela criação de novas instâncias desta classe.
	 * 
	 * @param context
	 */
	public FlowLayout( final Context context ) {

		super(context);
	}

	/**
	 * Responsável pela criação de novas instâncias desta classe.
	 * 
	 * @param context
	 * 
	 * @param attrs
	 */
	public FlowLayout( final Context context, final AttributeSet attrs ) {

		super(context, attrs);
	}

	/**
	 * Método responsável por mensurar o tamanho da tela do dispositivo.
	 * 
	 * @author Silvânio Júnior
	 * 
	 * @param widthMeasureSpec
	 * 
	 * @param heightMeasureSpec
	 */
	@Override
	protected void onMeasure(final int widthMeasureSpec, final int heightMeasureSpec) {

		assert ( MeasureSpec.getMode(widthMeasureSpec) != MeasureSpec.UNSPECIFIED );

		final int width = MeasureSpec.getSize(widthMeasureSpec) - this.getPaddingLeft() - this.getPaddingRight();

		int height = MeasureSpec.getSize(heightMeasureSpec) - this.getPaddingTop() - this.getPaddingBottom();

		final int count = this.getChildCount();

		int lineHeight = 0;

		int xpos = this.getPaddingLeft();

		int ypos = this.getPaddingTop();

		int childHeightMeasureSpec;

		if (MeasureSpec.getMode(heightMeasureSpec) == MeasureSpec.AT_MOST) {

			childHeightMeasureSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.AT_MOST);

		} else {

			childHeightMeasureSpec = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);
		}

		for (int i = 0; i < count; i++) {

			final View child = this.getChildAt(i);

			if (child.getVisibility() != View.GONE) {

				final LayoutParams lp = (LayoutParams) child.getLayoutParams();

				child.measure(MeasureSpec.makeMeasureSpec(width, MeasureSpec.AT_MOST), childHeightMeasureSpec);

				final int childw = child.getMeasuredWidth();

				lineHeight = Math.max(lineHeight, child.getMeasuredHeight() + lp.verticalSpacing);

				if (xpos + childw > width) {

					xpos = this.getPaddingLeft();

					ypos += lineHeight;
				}

				xpos += childw + lp.horizontalSpacing;
			}
		}

		this.lineHeight = lineHeight;

		if (MeasureSpec.getMode(heightMeasureSpec) == MeasureSpec.UNSPECIFIED) {

			height = ypos + lineHeight;

		} else if (MeasureSpec.getMode(heightMeasureSpec) == MeasureSpec.AT_MOST) {

			if (ypos + lineHeight < height) {

				height = ypos + lineHeight;
			}
		}

		this.setMeasuredDimension(width, height);
	}

	/**
	 * Método responsável por gerar os parametros default.
	 * 
	 * @author Silvânio Júnior
	 * 
	 * @return <code>LayoutParams</code>
	 */
	@Override
	protected ViewGroup.LayoutParams generateDefaultLayoutParams() {

		return new LayoutParams(1, 1);
	}

	/**
	 * Método responsável por validar parametros do layout.
	 * 
	 * @author Silvânio Júnior
	 * 
	 * @param p
	 * 
	 * @return <code>boolean</code>
	 */
	@Override
	protected boolean checkLayoutParams(final ViewGroup.LayoutParams p) {

		if (p instanceof LayoutParams) {

			return Boolean.TRUE;
		}

		return Boolean.FALSE;
	}

	/**
	 * Método responsável por desenhar o layout no dispositivo.
	 * 
	 * @author Silvânio Júnior
	 * 
	 * @param changed
	 * 
	 * @param l
	 * 
	 * @param t
	 * 
	 * @param r
	 * 
	 * @param b
	 */
	@Override
	protected void onLayout(final boolean changed, final int l, final int t, final int r, final int b) {

		final int count = this.getChildCount();

		final int width = r - l;

		int xpos = this.getPaddingLeft();

		int ypos = this.getPaddingTop();

		for (int i = 0; i < count; i++) {

			final View child = this.getChildAt(i);

			if (child.getVisibility() != View.GONE) {

				final int childw = child.getMeasuredWidth();

				final int childh = child.getMeasuredHeight();

				final LayoutParams lp = (LayoutParams) child.getLayoutParams();

				if (xpos + childw > width) {

					xpos = this.getPaddingLeft();

					ypos += this.lineHeight;
				}
				child.layout(xpos, ypos, xpos + childw, ypos + childh);

				xpos += childw + lp.horizontalSpacing;
			}
		}
	}

	/**
	 * <p>
	 * <b>Title:</b> LayoutParams
	 * </p>
	 * 
	 * <p>
	 * <b>Description:</b> Classe responsável por encapsular os paramentros mantidos pelo layout.
	 * </p>
	 * 
	 * <p>
	 * <b>Company: </b> FASAM
	 * </p>
	 * 
	 * @author Silvânio Júnior
	 * 
	 * @version 1.0.0
	 */
	public static class LayoutParams extends ViewGroup.LayoutParams {

		/** Atributo horizontalSpacing. */
		public final int	horizontalSpacing;

		/** Atributo verticalSpacing. */
		public final int	verticalSpacing;

		/**
		 * Responsável pela criação de novas instâncias desta classe.
		 * 
		 * @param horizontalSpacing
		 * 
		 * @param verticalSpacing
		 */
		public LayoutParams( final int horizontalSpacing, final int verticalSpacing ) {

			super(0, 0);

			this.horizontalSpacing = horizontalSpacing;

			this.verticalSpacing = verticalSpacing;
		}
	}
}
