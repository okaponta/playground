live_loop :melody do
    use_synth :tb303
    play 50, release: 0.1, cutoff: rrand(60, 120)
    sleep 0.109375
  end
  live_loop :boom do
    sample :loop_amen
    sleep 1.75
  end
  