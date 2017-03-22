//The following has to be in MainActivity class
@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set the content of the activity to use the activity_main.xml layout file
        setContentView(R.layout.activity_main);
        ColorsClickListener clickListener = new ColorsClickListener();
        TextView colors = (TextView) findViewById(R.id.colors);
        colors.setOnClickListener(clickListener);
    }



//Intent for another activity via an onClickListener
// This code has to be in ther OnClickListerner class which is created from the Main Activity
@Override
    public void onClick(View view) {
        // TODO Auto-generated method stub
        Intent intent=new Intent(view.getContext(),ChartImageActivity.class);
        intent.putExtra("chartLink", ChartLink);
        view.getContext().startActivity(intent);//Changed Here
    }
