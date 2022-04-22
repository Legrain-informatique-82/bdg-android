package fr.legrain.bdg.ui.article;

import androidx.databinding.DataBindingUtil;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

import fr.legrain.bdg.R;
import fr.legrain.bdg.databinding.FragmentArticleDetailBinding;
import fr.legrain.bdg.db.room.Article;

/**
     * A placeholder fragment containing a simple view.
     */
    public class ArticleDetailFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        public static final String ARG_SECTION_NUMBER = "section_number";
        public static final String ARG_SECTION_CONTENT = "section_content";

        public ArticleDetailFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static ArticleDetailFragment newInstance(int sectionNumber, Article article) {
            ArticleDetailFragment fragment = new ArticleDetailFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            args.putSerializable(ARG_SECTION_CONTENT, article);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_article_detail, container, false);

            Article data = (Article) getArguments().getSerializable(ARG_SECTION_CONTENT);

            FragmentArticleDetailBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_article_detail, container, false);
            binding.setArticle(data);

            ImageView imageView = (ImageView) binding.getRoot().findViewById(R.id.qrCode);
            if(data.getCodeBarre()!=null) {

                try {
                    Bitmap bitmap = textToImageEncode(data.getCodeBarre());
                    imageView.setImageBitmap(bitmap);
                } catch (WriterException e) {
                    e.printStackTrace();
                }
            } else {
                imageView.setVisibility(View.GONE);
            }


            //return rootView;
            return binding.getRoot();
        }

        /*
        https://stackoverflow.com/questions/28232116/android-using-zxing-generate-qr-code/30529128
        https://stackoverflow.com/questions/41606384/how-to-generate-qr-code-using-zxing-library
         */
        private int QRcodeWidth = 200;
        private int QRcodeHeight = 200;
        private Bitmap textToImageEncode(String Value) throws WriterException {
            BitMatrix bitMatrix;
            try {
                bitMatrix = new MultiFormatWriter().encode(
                        Value,
                        BarcodeFormat.DATA_MATRIX.QR_CODE,
                        QRcodeWidth, QRcodeHeight, null
                );

            } catch (IllegalArgumentException Illegalargumentexception) {

                return null;
            }
            int bitMatrixWidth = bitMatrix.getWidth();

            int bitMatrixHeight = bitMatrix.getHeight();

            int[] pixels = new int[bitMatrixWidth * bitMatrixHeight];

            for (int y = 0; y < bitMatrixHeight; y++) {
                int offset = y * bitMatrixWidth;

                for (int x = 0; x < bitMatrixWidth; x++) {

                    pixels[offset + x] = bitMatrix.get(x, y) ? Color.BLACK:Color.WHITE;
                }
            }
            Bitmap bitmap = Bitmap.createBitmap(bitMatrixWidth, bitMatrixHeight, Bitmap.Config.ARGB_4444);

            bitmap.setPixels(pixels, 0, QRcodeWidth, 0, 0, bitMatrixWidth, bitMatrixHeight);
            return bitmap;
        }

    }