package com.apppartner.androidprogrammertest;

import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.DragEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;


// 08/30 Add fadein-fadeout animation to AppPartner icon
// 08/30 Make AppPartner icon dragable
// 08/31 Add rotate animation to AppPartner icon
// 09/01 code cleanup

public class AnimationActivity extends AppCompatActivity{


    @Bind(R.id.appPartnerIcon)
    ImageView appPartnerIcon;

    @Bind(R.id.animateButton)
    ImageButton button;

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Bind(R.id.instructions)
    TextView instructions;

    @Bind(R.id.bonus)
    TextView bonusText;

    @Bind(R.id.toolBarText)
    TextView mToolBarText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);
        ButterKnife.bind(this);
        mToolBarText.setText("Animation");
        Typeface myTypeface = Typeface.createFromAsset(getAssets(), "fonts/Jelloween - Machinato ExtraLight.ttf");
        mToolBarText.setTypeface(myTypeface);
        button = (ImageButton) findViewById(R.id.animateButton);
        appPartnerIcon = (ImageView) findViewById(R.id.appPartnerIcon);


        Typeface custom_font1 = Typeface.createFromAsset(getAssets(),"fonts/Jelloween - Machinato ExtraLight.ttf");
        instructions.setTypeface(custom_font1);

        Typeface custom_font2 = Typeface.createFromAsset(getAssets(),"fonts/Jelloween - Machinato SemiBold Italic.ttf");
        bonusText.setTypeface(custom_font2);

        final Animation animationFadeInOut = AnimationUtils.loadAnimation(this, R.anim.fadeinfadeout);
        final Animation rotateAnimation = AnimationUtils.loadAnimation(this, R.anim.rot);

        //noinspection deprecation
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_arrow_back_white_24dp));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AnimationActivity.this, MainActivity.class));
                finish();

            }
        });

        //Set App partner icon to fade-in and fade-out on FADE button click
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                appPartnerIcon.startAnimation(animationFadeInOut);
                appPartnerIcon.setVisibility(view.VISIBLE);
            }
        });

        //set rotation animation
        appPartnerIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                view.startAnimation(rotateAnimation);

            }
        });

        //set longTouch listener and dragdrop
        appPartnerIcon.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                ClipData.Item item = new ClipData.Item((CharSequence) v.getTag());
                String[] mimeTypes = {ClipDescription.MIMETYPE_TEXT_PLAIN};
                ClipData dragData = new ClipData("AppIcon", mimeTypes, item);
                View.DragShadowBuilder myShadow = new View.DragShadowBuilder(appPartnerIcon);
                v.startDrag(dragData, myShadow, v, 0);
                return true;
            }
        });

        appPartnerIcon.getRootView().setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent dragEvent) {

                switch (dragEvent.getAction()) {

                    case DragEvent.ACTION_DROP:

                        float X2 = dragEvent.getX();
                        float Y2 = dragEvent.getY();
                        View view = (View) dragEvent.getLocalState();
                        view.setX(X2 - (view.getWidth() / 2));
                        view.setY(Y2 - (view.getHeight() / 2));
                        view.setVisibility(View.VISIBLE);
                        break;

                    default:
                        break;
                }
                return true;
            }
        });

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }


}
