% [num] = textread('result1.txt');
% standard = linspace(1,1,length(num));
% plot(num(:,1)./20,'b.-')
% hold on
% plot(num(:,2),'r.-')
% plot(standard,'k--')
% xlabel('k�Ĵ�С')
% ylabel('��һ����ľ�������ֵ')
% title('������Ƴ̶���k�ı仯���')
% legend('�й�/ns - docs1','һ�ž�����/t - docs1','��׼��')
% box on
% saveas(gcf,'matrix-k!0.png')

% %zero
% [num] = textread('result2.txt');
% standard = linspace(0,0,length(num));
% plot(num(:,1),'b.-')
% hold on
% plot(num(:,2),'r.-')
% plot(standard,'k--')
% xlabel('k�Ĵ�С')
% ylabel('��������ֵ')
% title('������Ƴ̶���k�ı仯���')
% legend('����/n - docs1','����/d - docs2','��׼��')
% box on
% saveas(gcf,'matrix-kis0.png')

% plot the eignvalue of Sigma
[eignvalue] = textread('Sdiag.txt');
plot(log(eignvalue),'r.-')
xlabel('rank')
ylabel('����ֵ��С')
title('����ֵ��С�ķֲ����ɣ����������£�')
box on
saveas(gcf,'eignvaluelog.png')