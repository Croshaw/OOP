import classes.Habitat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalTime;
import java.util.stream.Collectors;

public class AdvancedJFrame extends JFrame {
    private final JMenuBar menuBar;
    private final JMenu startMenuButton;
    private final JMenu stopMenuButton;
    private final JPanel mainPanel;
    private final JPanel menuPanel;
    private final JButton startButton;
    private final JButton stopButton;
    private final JCheckBox showInfoCBMenu;
    private final JCheckBox showInfoCBPanel;
    private final JLabel timerLabel;
    private final JRadioButton showTimerRB;
    private final JRadioButton hideTimerRB;
    private final JTextArea reportArea;
    private final JTextField n1;
    private final JComboBox p1;
    private final JTextField n2;
    private final JComboBox k1;
    private final JTextField lt1;
    private final JTextField lt2;
    private Habitat habitat;
    private final MouseAdapter startClick = new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            if(startButton.isEnabled())
                start();
        }
    };
    private final MouseAdapter stopClick = new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            if(stopButton.isEnabled())
                stop();
        }
    };
    private final KeyAdapter keyHandler = new KeyAdapter() {
        @Override
        public void keyTyped(KeyEvent e) {
            if(e.getKeyChar() == 't' || e.getKeyChar() == 'T' || e.getKeyChar() == 'е' || e.getKeyChar() == 'Е') {
                switchTimerVisibility();
            } else if (e.getKeyChar() == 'b' || e.getKeyChar() == 'B' || e.getKeyChar() == 'и' || e.getKeyChar() == 'И') {
                if(!stopButton.isEnabled())
                    start();
            } else if (e.getKeyChar() == 'e' || e.getKeyChar() == 'E' || e.getKeyChar() == 'у' || e.getKeyChar() == 'У') {
                if(!startButton.isEnabled())
                    stop();
            }
        }
    };

    private final KeyAdapter onlyDigital = new KeyAdapter() {
        @Override
        public void keyTyped(KeyEvent e) {
            if(!Character.isDigit(e.getKeyChar()))
                e.consume();
        }
    };

    public AdvancedJFrame(int width, int height) {
        this.setTitle("Lab 5");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();
        this.setBounds(dimension.width/2 - width/2, dimension.height/2 - height/2, width, height);
        this.setLayout(null);
        this.setResizable(false);

        menuBar = new JMenuBar();
        startMenuButton = new JMenu("Старт");
        startMenuButton.addMouseListener(startClick);
        stopMenuButton = new JMenu("Стоп");
        stopMenuButton.addMouseListener(stopClick);
        stopMenuButton.setEnabled(false);
        showInfoCBMenu = new JCheckBox("Показывать информацию");
        showInfoCBPanel = new JCheckBox("Показывать информацию");
        showInfoCBMenu.addItemListener(e -> showInfoCBPanel.setSelected(showInfoCBMenu.isSelected()));
        menuBar.add(startMenuButton);
        menuBar.add(stopMenuButton);
        menuBar.add(showInfoCBMenu);
        menuBar.setBounds(0,0, width, 20);

        mainPanel = new JPanel();
        mainPanel.setBounds(0,20, (int)(width*0.7), height-20);
        mainPanel.setOpaque(true);
        mainPanel.setBackground(Color.decode("#8768a1"));
        mainPanel.setLayout(null);
        mainPanel.addKeyListener(keyHandler);
        mainPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                mainPanel.requestFocus();
            }
        });

        menuPanel = new JPanel();
        menuPanel.setBounds(mainPanel.getWidth(), 20, width-mainPanel.getWidth(), height-20);
        menuPanel.setOpaque(true);
        menuPanel.setBackground(Color.decode("#252525"));
        menuPanel.setLayout(null);
        menuPanel.addKeyListener(keyHandler);
        menuPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                menuPanel.requestFocus();
            }
        });

        timerLabel = new JLabel("Время: 00:00:00");
        timerLabel.setBounds(mainPanel.getWidth()-100, 5, 100, 20);
        timerLabel.setForeground(Color.WHITE);

        reportArea = new JTextArea();
        reportArea.setEnabled(false);
        reportArea.setBounds((int) (mainPanel.getWidth()/2 - mainPanel.getWidth()/(1.5*2)), mainPanel.getHeight()/2 - 125, (int) (mainPanel.getWidth()/1.5), 200);
        reportArea.setOpaque(true);
        reportArea.setForeground(Color.WHITE);
        reportArea.setBackground(Color.decode("#8768a1"));
        reportArea.setFont(new Font(reportArea.getFont().getFontName(), Font.BOLD, 12));
        reportArea.setWrapStyleWord(true);
        reportArea.setLineWrap(true);

        n1 = new JTextField();
        n1.setBounds(5, mainPanel.getHeight()-65, 100, 20);
        n1.addKeyListener(onlyDigital);
        n1.setToolTipText("Период времени для рождения обычных кроликов");
        JLabel n1Label = new JLabel("n1:");
        n1Label.setBounds(5, mainPanel.getHeight()-85, 100, 20);
        n1Label.setForeground(Color.WHITE);

        p1 = new JComboBox(new Integer[]{1, 10, 20, 30, 40, 50, 60, 70, 80, 90, 100});
        p1.setBounds(115, mainPanel.getHeight()-65, 100, 20);
        p1.setToolTipText("Вероятность рождения обычных кроликов");
        JLabel p1Label = new JLabel("p1:");
        p1Label.setBounds(115, mainPanel.getHeight()-85, 100, 20);
        p1Label.setForeground(Color.WHITE);

        n2 = new JTextField();
        n2.setBounds(225, mainPanel.getHeight()-65, 100, 20);
        n2.addKeyListener(onlyDigital);
        n2.setToolTipText("Период времени для рождения кроликов альбиносов");
        JLabel n2Label = new JLabel("n2:");
        n2Label.setBounds(225, mainPanel.getHeight()-85, 100, 20);
        n2Label.setForeground(Color.WHITE);

        k1 = new JComboBox(new Integer[]{1, 10, 20, 30, 40, 50, 60, 70, 80, 90, 100});
        k1.setBounds(335, mainPanel.getHeight()-65, 100, 20);
        k1.setToolTipText("Максимальная часть кроликов альбиносов от общего кол-ва для рождения");
        JLabel k1Label = new JLabel("k1:");
        k1Label.setBounds(335, mainPanel.getHeight()-85, 100, 20);
        k1Label.setForeground(Color.WHITE);

        lt1 = new JTextField();
        lt1.setBounds(5, mainPanel.getHeight()-105, 100, 20);
        lt1.addKeyListener(onlyDigital);
        lt1.setToolTipText("Время жизни обычных кроликов");
        JLabel lt1Label = new JLabel("lt1:");
        lt1Label.setBounds(5, mainPanel.getHeight()-125, 100, 20);
        lt1Label.setForeground(Color.WHITE);

        lt2 = new JTextField();
        lt2.setBounds(115, mainPanel.getHeight()-105, 100, 20);
        lt2.addKeyListener(onlyDigital);
        lt2.setToolTipText("Время жизни кроликов альбиносов");
        JLabel lt2Label = new JLabel("lt2:");
        lt2Label.setBounds(115, mainPanel.getHeight()-125, 100, 20);
        lt2Label.setForeground(Color.WHITE);

        startButton = new JButton("Старт");
        startButton.addMouseListener(startClick);
        startButton.setBounds(0,0, menuPanel.getWidth(), 32);
        startButton.setOpaque(true);
        startButton.setBackground(Color.white);

        stopButton = new JButton("Стоп");
        stopButton.addMouseListener(stopClick);
        stopButton.setBounds(0,32, menuPanel.getWidth(), 32);
        stopButton.setOpaque(true);
        stopButton.setBackground(Color.white);
        stopButton.setEnabled(false);

        showInfoCBPanel.addItemListener(e -> showInfoCBMenu.setSelected(showInfoCBPanel.isSelected()));
        showInfoCBPanel.setBounds(0, 64, menuPanel.getWidth(), 20);
        showInfoCBPanel.setOpaque(true);
        showInfoCBPanel.setBackground(Color.white);

        showTimerRB = new JRadioButton("<html>Показывать время симуляции</html>");
        showTimerRB.setBounds(0, 86, menuPanel.getWidth(), 40);
        showTimerRB.setSelected(true);
        showTimerRB.setOpaque(true);
        showTimerRB.setBackground(Color.white);
        showTimerRB.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                switchTimerVisibility();
            }
        });
        hideTimerRB = new JRadioButton("<html>Скрывать время симуляции</html>");
        hideTimerRB.setBounds(0, 126, menuPanel.getWidth()-10, 40);
        hideTimerRB.setOpaque(true);
        hideTimerRB.setBackground(Color.white);
        hideTimerRB.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                switchTimerVisibility();
            }
        });

        JButton currentObjectsButton = new JButton("Текущие объекты");
        currentObjectsButton.setBounds(0, 206, menuPanel.getWidth(), 20);
        currentObjectsButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(habitat != null && !habitat.isStop())
                    JOptionPane.showMessageDialog(null, habitat.rabbitsMap.keySet().stream().map(rabbit -> "%s - %s".formatted(habitat.rabbitsMap.get(rabbit), rabbit.getId())).collect(Collectors.joining("\n")), "Текущие объекты", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        mainPanel.add(timerLabel);
        mainPanel.add(reportArea);
        mainPanel.add(n1);
        mainPanel.add(n1Label);
        mainPanel.add(p1);
        mainPanel.add(p1Label);
        mainPanel.add(n2);
        mainPanel.add(n2Label);
        mainPanel.add(k1);
        mainPanel.add(k1Label);
        mainPanel.add(lt1);
        mainPanel.add(lt1Label);
        mainPanel.add(lt2);
        mainPanel.add(lt2Label);

        menuPanel.add(startButton);
        menuPanel.add(stopButton);
        menuPanel.add(showInfoCBPanel);
        menuPanel.add(showTimerRB);
        menuPanel.add(hideTimerRB);
        menuPanel.add(currentObjectsButton);

        this.add(menuBar);
        this.add(mainPanel);
        this.add(menuPanel);
        this.setVisible(true);
    }

    private void switchTimerVisibility() {
        timerLabel.setVisible(!timerLabel.isVisible());
        showTimerRB.setSelected(timerLabel.isVisible());
        hideTimerRB.setSelected(!timerLabel.isVisible());
    }

    private void start() {
        if(n1.getText().isEmpty() || n2.getText().isEmpty() || lt1.getText().isEmpty() || lt2.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Заполнить поля!", "Внимание", JOptionPane.WARNING_MESSAGE);
            return;
        }
        reportArea.setText("");
        timerLabel.setForeground(Color.RED);
        startButton.setEnabled(false);
        startMenuButton.setEnabled(false);
        stopButton.setEnabled(true);
        stopMenuButton.setEnabled(true);
        habitat = new Habitat(Integer.parseInt(n1.getText()), ((float)((Integer)p1.getSelectedItem())/100), Integer.parseInt(n2.getText()), (Integer) k1.getSelectedItem()
        ,Integer.parseInt(lt1.getText()), Integer.parseInt(lt2.getText()));
        habitat.Start();
        Thread thread = new Thread(() -> {
            while(!habitat.isStop()) {
                habitat.Update();
                timerLabel.setText("Время: %s".formatted(habitat.getDuration() == 0 ? "00:00:00" : LocalTime.of(0,0,habitat.getDuration())));
            }
        });
        thread.start();
    }

    private void stop() {
        if(showInfoCBMenu.isSelected()) {
            if(JOptionPane.showConfirmDialog(this, habitat.toString(), "Вы уверены?", JOptionPane.OK_CANCEL_OPTION) == 2)
                return;
        }
        timerLabel.setForeground(Color.WHITE);
        startButton.setEnabled(true);
        startMenuButton.setEnabled(true);
        stopButton.setEnabled(false);
        stopMenuButton.setEnabled(false);
        habitat.Stop();
        reportArea.setText(habitat.getReport());
    }
}
